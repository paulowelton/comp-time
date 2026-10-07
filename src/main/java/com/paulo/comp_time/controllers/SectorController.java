package com.paulo.comp_time.controllers;

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

    private final SectorService sectorService;

    @GetMapping
    public ResponseEntity<List<SectorResponse>> getAll(
            @RequestParam(defaultValue = "false") boolean includeInactive) {

        return ResponseEntity.ok(sectorService.getAll(includeInactive));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SectorResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(sectorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<SectorResponse> create(@RequestBody @Valid SectorRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sectorService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SectorResponse> update(
            @RequestBody @Valid SectorRequest request,
            @PathVariable Long id) {

        return ResponseEntity.ok(sectorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        sectorService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/deactivate")
    public ResponseEntity<SectorResponse> deactivate(
            @PathVariable Long id) {

        return ResponseEntity.ok(sectorService.deactivate(id));
    }

    @PostMapping("/{id}/activate")
    public ResponseEntity<SectorResponse> activate(
            @PathVariable Long id) {

        return ResponseEntity.ok(sectorService.activate(id));
    }
}
