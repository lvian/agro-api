package com.yara.agroapi.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class WeatherApiResponseDeserializer extends JsonDeserializer<WeatherApiResponse> {
  @Override
  public WeatherApiResponse deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
    ObjectCodec oc = jp.getCodec();
    JsonNode node = oc.readTree(jp);

    String timestamp = node.get("dt").asText();
    Double temperature = node.get("main").get("temp").asDouble();
    Double humidity = node.get("main").get("humidity").asDouble();
    Double temperatureMax = node.get("main").get("temp_max").asDouble();
    Double temperatureMin = node.get("main").get("temp_min").asDouble();

    return new WeatherApiResponse(timestamp, temperature, humidity, temperatureMax, temperatureMin);
  }
}