package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.Sector;
import com.paulo.comp_time.dtos.requests.SectorRequest;
import com.paulo.comp_time.dtos.responses.SectorResponse;
import com.paulo.comp_time.exceptions.SectorNotFoundException;
import com.paulo.comp_time.repositories.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorService {

    private SectorRepository sectorRepository;

    public List<Sector> getAll(boolean includeInactive) {

        if (includeInactive) {
            return sectorRepository.findAll();
        }

        return sectorRepository.findAllByActiveTrue();
    }

    public Sector getById(String id) {
        return sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));
    }

    public Sector create(SectorRequest request) {
        Sector sector = new Sector(
                request.name()
        );

        return sectorRepository.save(sector);
    }

    public Sector update(String id, SectorRequest request) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sector.setName(request.name());

        return sectorRepository.save(sector);
    }

    public void delete(String id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sectorRepository.delete(sector);
    }

    public Sector deactivate(String id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sector.setActive(false);

        return sectorRepository.save(sector);
    }

    public Sector activate(String id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sector.setActive(true);

        return sectorRepository.save(sector);
    }

}
