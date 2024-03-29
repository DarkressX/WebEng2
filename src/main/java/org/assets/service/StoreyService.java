package org.assets.service;

import org.assets.model.Buildings;
import org.assets.model.Storeys;
import org.assets.repository.BuildingRepository;
import org.assets.repository.RoomRepository;
import org.assets.repository.StoreyRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class StoreyService
{
    private final StoreyRepository storeyRepository;
    private final BuildingRepository buildingRepository;
    private final RoomRepository roomRepository;

    public StoreyService(StoreyRepository storeyRepository, BuildingRepository buildingRepository, RoomRepository roomRepository) {
        this.storeyRepository = storeyRepository;
        this.buildingRepository = buildingRepository;
        this.roomRepository = roomRepository;
    }

    public List<Storeys> getAllStoreys(Boolean include_deleted, UUID building_id) {
        if(include_deleted) {
            return storeyRepository.findAllByBuilding_Id(building_id);
        }
        return storeyRepository.findStoreysByDeletedAtIsNullAndBuilding_Id(building_id);
    }

    public Storeys getStoreyByID(UUID id) {
        return storeyRepository.findStoreyById(id);
    }

    public Storeys saveStorey(Storeys newStorey) {
        newStorey.setId(UUID.randomUUID());
        Buildings building = buildingRepository.findBuildingById(newStorey.getBuildingID());
        if(building == null || building.getDeletedAt() != null) {
            throw new IllegalArgumentException();
        }
        return storeyRepository.save(newStorey);
    }

    public Storeys updateStoreyByID(UUID id, Storeys newStorey, boolean restore) {
        Storeys oldStorey = storeyRepository.findStoreyById(id);
        Buildings building = buildingRepository.findBuildingById(newStorey.getBuildingID());
        if(building == null || building.getDeletedAt() != null) {
            throw new IllegalArgumentException();
        }
        if(oldStorey != null && oldStorey.getDeletedAt() != null && !restore) {
            //Storey is deleted but no
            // restore was requested
            throw new UnsupportedOperationException();
        }
        if(oldStorey == null) {
            throw new NoSuchElementException();
        }
        newStorey.setId(id);
        return storeyRepository.save(newStorey);
    }

    public Storeys saveStoreyByID(UUID id, Storeys newStorey) {
        newStorey.setId(id);
        return storeyRepository.save(newStorey);
    }

    public void deleteStorey(UUID id) throws IllegalArgumentException {
        Storeys storey = storeyRepository.findStoreyById(id);
        if(storey == null || storey.getDeletedAt() != null){
            throw new NoSuchElementException();
        }
        if(!roomRepository.findRoomsByDeletedAtIsNullAndStorey_Id(id).isEmpty()) {
            throw new IllegalArgumentException();
        }
        storey.setDeletedAt(java.time.LocalDateTime.now()); //TODO: Check if room exist in this storey
    }
}
