package org.assets.service;

import org.assets.model.Buildings;
import org.assets.model.Storeys;
import org.assets.repository.BuildingRepository;
import org.assets.repository.StoreyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class BuildingService
{
    private final BuildingRepository buildingRepository;

    private final StoreyRepository storeyRepository;

    public BuildingService(BuildingRepository buildingRepository, StoreyRepository storeyRepository) {
        this.buildingRepository = buildingRepository;
        this.storeyRepository = storeyRepository;
    }

    public List<Buildings> getAllBuildings(Boolean deleted_at) {
        if(deleted_at) {
            return buildingRepository.findAll();
        }
        return buildingRepository.findBuildingsByDeletedAtIsNull();
    }

    public Buildings getBuildingByID(UUID id) {
        return buildingRepository.findBuildingById(id);
    }

    public Buildings saveBuilding(Buildings newBuilding) {
        newBuilding.setId(UUID.randomUUID());
        return buildingRepository.save(newBuilding);
    }

    public Buildings updateBuildingByID(UUID id, Buildings newBuilding, boolean restore) {
        Buildings oldBuilding = buildingRepository.findBuildingById(id);
        if(oldBuilding != null && oldBuilding.getDeletedAt() != null && !restore) {
            //Building is deleted but no
            // restore was requested
            throw new UnsupportedOperationException();
        }
        newBuilding.setId(id);
        return buildingRepository.save(newBuilding);
    }

    public void deleteBuilding(UUID id) throws IllegalArgumentException {
        Buildings building = buildingRepository.findBuildingById(id);
        if(building == null || building.getDeletedAt() != null){
            throw new NoSuchElementException();
        }
        if(!storeyRepository.findStoreysByDeletedAtIsNullAndBuilding_Id(id).isEmpty()) {
            throw new IllegalArgumentException();
        }
        building.setDeletedAt(java.time.LocalDateTime.now());
    }
}
