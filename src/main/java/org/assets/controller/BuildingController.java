package org.assets.controller;

import org.assets.model.Buildings;
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
    public ResponseEntity<List<Buildings>> getBuildings()
    {
        return ResponseEntity.ok().body(buildingService.getAllBuildings());
    }

    @PostMapping("/buildings")
    public ResponseEntity<Buildings> saveBuilding(@RequestBody Buildings buildings) {
        Buildings newBuilding = buildingService.saveBuilding(buildings);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newBuilding.getId()).toUri();

        return ResponseEntity.created(uri).body(newBuilding);
    }

    @GetMapping("/buildings/{id}")
    public ResponseEntity<Buildings> getBuildingByID(@PathVariable UUID id) {
        return ResponseEntity.ok().body(buildingService.getBuildingByID(id));
    }
}
