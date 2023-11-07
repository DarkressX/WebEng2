package org.assets.controller;

import org.assets.model.Building;
import org.assets.repository.BuildingRepository;
import org.assets.service.BuildingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok().body(buildingService.saveBuilding(building));
    }
}
