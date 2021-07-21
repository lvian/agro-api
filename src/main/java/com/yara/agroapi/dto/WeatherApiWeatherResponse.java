package com.yara.agroapi.dto;

import com.sun.istack.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiWeatherResponse {

  private List<WeatherApiResponse> weather;
}

