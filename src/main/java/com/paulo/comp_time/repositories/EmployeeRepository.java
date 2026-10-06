package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByCpf(String cpf);
    List<Employee> findAllByActiveTrue();
}
