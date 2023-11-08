package org.assets.service;

import org.assets.model.Buildings;
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

    public List<Buildings> getAllBuildings() {
        return buildingRepository.findAll();
    }

    public Buildings getBuildingByID(UUID id) {
        return buildingRepository.findBuildingById(id);
    }

    public Buildings saveBuilding(Buildings buildings) {
        return buildingRepository.save(buildings);
    }
}
