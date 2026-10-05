package com.paulo.comp_time.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "job_positions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JobPosition {

    public JobPosition(@NotBlank String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private Boolean active = true;
}
