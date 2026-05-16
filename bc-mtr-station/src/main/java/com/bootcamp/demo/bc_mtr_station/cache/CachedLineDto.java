package com.bootcamp.demo.bc_mtr_station.cache;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CachedLineDto {
  private String lineName;
  private List<Map<String, String>> stations;
}
