package com.paulo.comp_time.controllers;

import com.paulo.comp_time.domain.entities.Sector;
import com.paulo.comp_time.dtos.requests.SectorRequest;
import com.paulo.comp_time.dtos.responses.SectorResponse;
import com.paulo.comp_time.services.SectorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sectors")
public class SectorController {

    @Autowired
    private SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<Sector>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        return ResponseEntity.ok(sectorService.getAll(includeInactive));
    }

    @PostMapping("/{id}")
    public ResponseEntity<SectorResponse> getById(@PathVariable String id) {

        Sector sector = sectorService.getById(id);

        return ResponseEntity.ok(new SectorResponse(sector.getName()));
    }

    @PostMapping
    public ResponseEntity<SectorResponse> create(@RequestBody @Valid SectorRequest request) {

        Sector sector = sectorService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new SectorResponse(sector.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorResponse> update(
            @RequestBody @Valid SectorRequest request,
            @PathVariable String id) {

        Sector sector = sectorService.update(id, request);

        return  ResponseEntity.ok(new SectorResponse(sector.getName()));
    }

}
