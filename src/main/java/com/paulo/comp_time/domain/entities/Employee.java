package com.paulo.comp_time.domain.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private int sectorId;
    private int jobPositionId;
    private boolean active;
}