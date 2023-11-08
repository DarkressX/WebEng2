package org.assets.service;

import org.assets.model.Building;
import org.assets.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BuildingService
{
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    public List<Building> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Building getBuildingByID(UUID id) {
        return buildingRepository.findBuildingById(id);
    }

    public Building saveBuilding(Building building) {
        return buildingRepository.save(building);
    }
}
