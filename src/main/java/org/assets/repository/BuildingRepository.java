package org.assets.repository;

import org.assets.model.Buildings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuildingRepository extends JpaRepository<Buildings, UUID>
{
    Buildings findBuildingById(UUID id);

    Buildings findBuildingByAddress(String address);
}