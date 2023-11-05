package org.common.common.repository;

import org.common.common.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long>
{
    Building findByName(String name);

    Building findBuildingById(Long id);

    Building findBuildingByAddress(String address);
}