package com.bootcamp.demo.bc_mtr_station.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "line_station")
public class LineStationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Setter
  @ManyToOne
  @Column(name = "line", nullable = false)
  @JoinColumn
  private LineEntity lineEntity;

  @Setter
  @ManyToOne
  @Column(name = "station", nullable = false)
  @JoinColumn
  private StationEntity stationEntity;
}
