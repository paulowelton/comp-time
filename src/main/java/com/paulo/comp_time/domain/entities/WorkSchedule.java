package com.paulo.comp_time.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Table(name = "work_schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalTime startTime;
    private LocalTime endTime;
    private int breakSeconds = 0;
    private int expectedSeconds;
    private boolean active = true;
}
