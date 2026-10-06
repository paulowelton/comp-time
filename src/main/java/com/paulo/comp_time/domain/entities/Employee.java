package com.paulo.comp_time.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private int sectorId;
    private int jobPositionId;
    private boolean active;

    public Employee(String name, String cpf, Integer sectorId, Integer jobPositionId) {
        this.name = name;
        this.cpf = cpf;
        this.sectorId = sectorId;
        this.jobPositionId = jobPositionId;
    }
}