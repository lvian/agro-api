package com.yara.agroapi.dto;

import com.sun.istack.NotNull;
import com.yara.agroapi.entity.GeoJson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiRequest {
  @NotNull
  private String name;
  @NotNull
  private GeoJson geo_jon;
}



