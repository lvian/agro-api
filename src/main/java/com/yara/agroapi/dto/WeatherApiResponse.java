package com.yara.agroapi.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = WeatherApiResponseDeserializer.class)
public class WeatherApiResponse {
  @NotNull
  private String timestamp;
  @NotNull
  private Double temperature;
  @NotNull
  private Double humidity;
  @NotNull
  private Double temperatureMax;
  @NotNull
  private Double temperatureMin;
}

