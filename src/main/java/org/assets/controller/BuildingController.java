package org.assets.controller;

import org.assets.model.Building;
import org.assets.repository.BuildingRepository;
import org.assets.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
public class BuildingController
{
    private final BuildingService buildingService;
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("/buildings")
    public ResponseEntity<List<Building>> getBuildings()
    {
        return ResponseEntity.ok().body(buildingService.getAllBuildings());
    }

    @PostMapping("/buildings")
    public ResponseEntity<Building> saveBuilding(@RequestBody Building building) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/buildings").toUriString());
        return ResponseEntity.created(uri).body(buildingService.saveBuilding(building));
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<Building> getBuildingByID(@PathVariable UUID id) {
        return ResponseEntity.ok().body(buildingService.getBuildingByID(id));
    }
}
