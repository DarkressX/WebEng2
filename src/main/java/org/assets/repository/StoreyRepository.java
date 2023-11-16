package org.assets.repository;

import org.assets.model.Storeys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreyRepository extends JpaRepository<Storeys, UUID>
{
    Storeys findStoreyById(UUID id);

    List<Storeys> findAllByBuilding_Id(UUID building_id);

    List<Storeys> findStoreysByDeletedAtIsNullAndBuilding_Id(UUID building_id);
}