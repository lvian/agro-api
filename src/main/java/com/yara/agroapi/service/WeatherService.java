package com.yara.agroapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yara.agroapi.dto.WeatherApiRequest;
import com.yara.agroapi.dto.WeatherApiPolygonResponse;
import com.yara.agroapi.dto.WeatherApiResponse;
import com.yara.agroapi.dto.WeatherApiWeatherResponse;
import com.yara.agroapi.entity.Fields;
import com.yara.agroapi.entity.GeoJson;
import com.yara.agroapi.repository.FieldsRepository;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class WeatherService {
  @Autowired
  private FieldsRepository fieldRepository;

  @Autowired
  WebClient webClient;

  @Autowired
  ClientAndServer mockServer;

  @Bean
  public ClientAndServer startServer() throws IOException {
    ClientAndServer mockServer = ClientAndServer.startClientAndServer(8899);

    InputStream polygonResource = new ClassPathResource("polygonResponse.json").getInputStream();
    String polygonResponse = new String(polygonResource.readAllBytes());

    // set up mock server with a delay of 1 seconds
    mockServer.when(HttpRequest.request().withMethod("POST")
        .withPath("/polygons")).
        respond(HttpResponse.response()
            .withBody(polygonResponse)
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withDelay(TimeUnit.MILLISECONDS, 500));

    InputStream weatherResource = new ClassPathResource("weatherResponse.json").getInputStream();
    String weatherResponse = new String(weatherResource.readAllBytes());

    // set up mock server with a delay of 1 seconds
    mockServer.when(HttpRequest.request().withMethod("GET")
        .withPath("/history")).
        respond(HttpResponse.response()
            .withBody(weatherResponse)
            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .withDelay(TimeUnit.MILLISECONDS, 500));


    return mockServer;
  }

  @Bean
  public WebClient client() {
    final String baseUrl = "http://localhost:8899";
    return  WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }


  public Object getFieldWeatherHistory(Fields fields) throws JsonProcessingException {

    GeoJson geoJson = fields.getBoundaries().getGeoJson();

    WeatherApiRequest weatherApiRequest = WeatherApiRequest.builder()
        .name(fields.getName())
        .geo_jon(geoJson)
        .build();

    WeatherApiPolygonResponse polygonResponse = webClient.post()
        .uri("/polygons?appid=test")
        .body(Mono.just(weatherApiRequest), WeatherApiRequest.class)
        .retrieve()
        .bodyToMono(WeatherApiPolygonResponse.class)
        .block();


    String lat = polygonResponse.getCenter().get(0).toString();
    String lon = polygonResponse.getCenter().get(1).toString();

    long start = Instant.now().getEpochSecond();
    long end = Instant.now().minus( 7, ChronoUnit.DAYS).getEpochSecond();


    Flux<WeatherApiResponse> flux = webClient.get()
        .uri(uriBuilder -> uriBuilder
            .path("/history")
            .queryParam("lat", lat)
            .queryParam("lon", lon)
            .queryParam("start",start)
            .queryParam("end", end)
            .queryParam("appid", "test")
            .build())
        .retrieve()
        .bodyToFlux(WeatherApiResponse.class);

    List<WeatherApiResponse> weatherList = flux.collectList().block();

    WeatherApiWeatherResponse weatherApiWeatherResponse = new WeatherApiWeatherResponse();
    weatherApiWeatherResponse.setWeather(weatherList);
    return weatherApiWeatherResponse;
  }
}
