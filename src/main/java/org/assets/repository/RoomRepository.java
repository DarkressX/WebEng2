package org.assets.repository;

import org.assets.model.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, UUID>
{
    Rooms findRoomById(UUID id);

    List<Rooms> findAllByStorey_Id(UUID storey_id);

    List<Rooms> findRoomsByDeletedAtIsNullAndStorey_Id(UUID storey_id);
}