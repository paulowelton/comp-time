package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.Sector;
import com.paulo.comp_time.dtos.requests.SectorRequest;
import com.paulo.comp_time.dtos.responses.SectorResponse;
import com.paulo.comp_time.services.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sectors")
@RequiredArgsConstructor
public class SectorController {

    private SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        List<SectorResponse> sectors = sectorService
                .getAll(includeInactive)
                .stream()
                .map(sector -> new SectorResponse(
                        sector.getName(),
                        sector.getActive()
                ))
                .toList();

        return ResponseEntity.ok(sectors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorResponse> getById(@PathVariable String id) {

        Sector sector = sectorService.getById(id);

        return ResponseEntity.ok(new SectorResponse(sector.getName(), sector.getActive()));
    }

    @PostMapping
    public ResponseEntity<SectorResponse> create(@RequestBody @Valid SectorRequest request) {

        Sector sector = sectorService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SectorResponse(sector.getName(), sector.getActive()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorResponse> update(
            @RequestBody @Valid SectorRequest request,
            @PathVariable String id) {

        Sector sector = sectorService.update(id, request);

        return  ResponseEntity.ok(new SectorResponse(sector.getName(), sector.getActive()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id
    ) {

        sectorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<SectorResponse> deactivate(
            @PathVariable String id) {

        Sector sector = sectorService.deactivate(id);

        return ResponseEntity.ok(new SectorResponse(sector.getName(), sector.getActive()));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<SectorResponse> activate(
            @PathVariable String id) {

        Sector sector = sectorService.activate(id);

        return ResponseEntity.ok(
                new SectorResponse(
                        sector.getName(),
                        sector.getActive()
                )
        );
    }
}
