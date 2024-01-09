package org.assets.service;

import org.assets.model.Storeys;
import org.assets.model.Rooms;
import org.assets.repository.StoreyRepository;
import org.assets.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
public class RoomService
{
    private final RoomRepository roomRepository;
    private final StoreyRepository storeyRepository;

    public RoomService(RoomRepository roomRepository, StoreyRepository storeyRepository) {
        this.roomRepository = roomRepository;
        this.storeyRepository = storeyRepository;
    }

    public List<Rooms> getAllRooms(Boolean include_deleted, UUID storey_id) {
        if(include_deleted) {
            return roomRepository.findAllByStorey_Id(storey_id);
        }
        return roomRepository.findRoomsByDeletedAtIsNullAndStorey_Id(storey_id);
    }

    public Rooms getRoomByID(UUID id) {
        return roomRepository.findRoomById(id);
    }

    public Rooms saveRoom(Rooms newRoom) {
        newRoom.setId(UUID.randomUUID());
        Storeys storey = storeyRepository.findStoreyById(newRoom.getStoreyID());
        if(storey == null || storey.getDeletedAt() != null) {
            throw new IllegalArgumentException();
        }
        return roomRepository.save(newRoom);
    }

    public Rooms updateRoomByID(UUID id, Rooms newRoom, boolean restore) {
        Rooms oldRoom = roomRepository.findRoomById(id);
        Storeys storey = storeyRepository.findStoreyById(newRoom.getStoreyID());
        if(storey == null || storey.getDeletedAt() != null) {
            throw new IllegalArgumentException();
        }
        if(oldRoom != null && oldRoom.getDeletedAt() != null && !restore) {
            //Room is deleted but no
            // restore was requested
            throw new UnsupportedOperationException();
        }
        if(oldRoom == null) {
            throw new NoSuchElementException();
        }
        newRoom.setId(id);
        return roomRepository.save(newRoom);
    }

    public Rooms saveRoomByID(UUID id, Rooms newRoom) {
        newRoom.setId(id);
        return roomRepository.save(newRoom);
    }

    public void deleteRoom(UUID id) throws IllegalArgumentException {
        Rooms room = roomRepository.findRoomById(id);
        if(room == null || room.getDeletedAt() != null){
            throw new NoSuchElementException();
        }
        room.setDeletedAt(java.time.LocalDateTime.now()); //TODO: Check if room exist in this room
    }
}
