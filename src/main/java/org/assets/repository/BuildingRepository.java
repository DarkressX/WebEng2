package org.assets.repository;

import org.assets.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long>
{
    Building findBuildingById(Long id);

    Building findBuildingByAddress(String address);
}