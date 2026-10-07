package com.paulo.comp_time.services;

import com.paulo.comp_time.domain.entities.Sector;
import com.paulo.comp_time.dtos.requests.SectorRequest;
import com.paulo.comp_time.dtos.responses.SectorResponse;
import com.paulo.comp_time.exceptions.SectorNotFoundException;
import com.paulo.comp_time.repositories.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;

    public List<SectorResponse> getAll(boolean includeInactive) {
        List<Sector> sectors = includeInactive
                ? sectorRepository.findAll()
                : sectorRepository.findAllByActiveTrue();

        return sectors.stream()
                .map(sector -> new SectorResponse(
                        sector.getId(),
                        sector.getName(),
                        sector.getActive()
                ))
                .toList();
    }

    public SectorResponse getById(Long id) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        return new SectorResponse(
                sector.getId(),
                sector.getName(),
                sector.getActive()
        );
    }

    public SectorResponse create(SectorRequest request) {
        Sector sector = new Sector(
                request.name()
        );

        sectorRepository.save(sector);

        return new SectorResponse(
                sector.getId(),
                sector.getName(),
                sector.getActive()
        );
    }

    public SectorResponse update(Long id, SectorRequest request) {
        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sector.setName(request.name());

        sectorRepository.save(sector);

        return new SectorResponse(
                sector.getId(),
                sector.getName(),
                sector.getActive()
        );
    }

    public void delete(Long id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sectorRepository.delete(sector);
    }

    public SectorResponse deactivate(Long id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sector.setActive(false);

        sectorRepository.save(sector);

        return new SectorResponse(
                sector.getId(),
                sector.getName(),
                sector.getActive()
        );
    }

    public SectorResponse activate(Long id) {

        Sector sector = sectorRepository.findById(id)
                .orElseThrow(() -> new SectorNotFoundException("Sector not found"));

        sector.setActive(true);

        sectorRepository.save(sector);

        return new SectorResponse(
                sector.getId(),
                sector.getName(),
                sector.getActive()
        );
    }

}
