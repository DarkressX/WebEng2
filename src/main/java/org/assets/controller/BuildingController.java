package org.assets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assets.model.Buildings;
import org.assets.service.BuildingService;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/buildings")
public class BuildingController
{
    private final BuildingService buildingService;
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping("")
    public ResponseEntity<List<Buildings>> getBuildings()
    {
        return ResponseEntity.ok().body(buildingService.getAllBuildings());
    }

    @PostMapping("")
    public ResponseEntity<Buildings> saveBuilding(@RequestBody Buildings buildings) {
        Buildings newBuilding = buildingService.saveBuilding(buildings);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newBuilding.getId()).toUri();

        return ResponseEntity.created(uri).body(newBuilding);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buildings> getBuildingByID(@PathVariable UUID id) {
        if (buildingService.getBuildingByID(id) != null) {
            return ResponseEntity.ok().body(buildingService.getBuildingByID(id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Buildings> deleteBuilding(@PathVariable UUID id) {
        try {
            buildingService.deleteBuilding(id);
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build(); //Building does not exist or already deleted
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Buildings> putBuilding(@PathVariable UUID id, @RequestBody Map<String, Object> requestBody) {
        Boolean restore = false;
        Buildings buildings = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            buildings = objectMapper.convertValue(requestBody, Buildings.class);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

        if(requestBody.containsKey("deleted_at") && requestBody.get("deleted_at") == null) {
            restore = true;
        }
        if (buildingService.getBuildingByID(id) != null) {
            try {
                return ResponseEntity.ok().body(buildingService.updateBuildingByID(id, buildings, restore));
            }
            catch(UnsupportedOperationException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        buildings.setId(id);
        return ResponseEntity.created(uri).body(buildingService.updateBuildingByID(id, buildings, false));
    }
}
