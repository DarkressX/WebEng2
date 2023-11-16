package org.assets.service;

import org.assets.model.Buildings;
import org.assets.repository.BuildingRepository;
import org.hibernate.query.criteria.internal.expression.function.CurrentTimeFunction;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class BuildingService
{
    private final BuildingRepository buildingRepository;

    public BuildingService(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
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

    public Buildings updateBuildingByID(UUID id, Buildings newBuilding, Boolean restore) {
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
        building.setDeletedAt(java.time.LocalDateTime.now()); //TODO: Check if storeys exist in this building
    }
}
