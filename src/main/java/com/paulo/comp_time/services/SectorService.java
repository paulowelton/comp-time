package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.Sector;
import com.paulo.comp_time.dtos.requests.SectorRequest;
import com.paulo.comp_time.repositories.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    @Autowired
    private SectorRepository sectorRepository;

    public List<Sector> getAll(boolean includeInactive) {

        if (includeInactive) {
            return sectorRepository.findAll();
        }

        return sectorRepository.findAllByActiveTrue();
    }

    public Sector getById(String id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found"));
    }

    public Sector create(SectorRequest request) {
        Sector sector = new Sector(
                request.name()
        );

        return sectorRepository.save(sector);
    }

    public Sector update(String id, SectorRequest request) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sector not found"));

        sector.setName(request.name());

        return sectorRepository.save(sector);
    }


}
