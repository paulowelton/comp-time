package com.paulo.comp_time.repositories;

import com.paulo.comp_time.domain.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectorRepository extends JpaRepository<Sector, Long> {
    List<Sector> findAllByActiveTrue();
}
