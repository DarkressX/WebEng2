package org.assets.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="storeys")
public class Storeys
{
    @Id
    private UUID id;

    @NotBlank(message = "Storey name is required")
    private String name;

    @JsonAlias("building_id")
    @NotBlank(message = "Building id required")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="building_id")
    private Buildings building;

    @JsonAlias("deleted_at")
    private LocalDateTime deletedAt;

    public Storeys(UUID id, String name, Buildings building, LocalDateTime deletedAt)
    {
        this.id = id;
        this.name = name;
        this.building = building;
        this.deletedAt = deletedAt;
    }

    public Storeys(){}

    public UUID getId() {return id;}

    public String getName() {return name;}

    public LocalDateTime getDeletedAt()
    {
        return deletedAt;
    }

    public UUID getBuildingID()
    {
        return building.getId();
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setBuilding(Buildings building)
    {
        this.building = building;
    }

    public void setDeletedAt(LocalDateTime deletedAt)
    {
        this.deletedAt = deletedAt;
    }
}
