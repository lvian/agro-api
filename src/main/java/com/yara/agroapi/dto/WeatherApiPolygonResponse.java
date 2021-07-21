package com.yara.agroapi.dto;

import com.sun.istack.NotNull;
import com.yara.agroapi.entity.GeoJson;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherApiPolygonResponse {
  @NotNull
  private String id;
  @NotNull
  private GeoJson geo_jon;
  @NotNull
  private String name;
  @NotNull
  private ArrayList center;
  @NotNull
  private Double area;
  @NotNull
  private String user_id;
}

