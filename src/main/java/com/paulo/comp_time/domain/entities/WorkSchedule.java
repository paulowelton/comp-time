package com.paulo.comp_time.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "work_schedules")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private LocalDate start_time;
    private LocalDate end_time;
    private int break_seconds;
    private int expected_seconds;
    private boolean active = true;
}
