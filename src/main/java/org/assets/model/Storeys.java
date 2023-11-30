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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="building_id", insertable = false, updatable = false)
    private Buildings building;

    @JsonAlias("building_id")
    @Column(name = "building_id")
    private UUID buildingID;

    @JsonAlias("deleted_at")
    private LocalDateTime deletedAt;

    public Storeys(UUID id, String name, UUID buildingID, LocalDateTime deletedAt)
    {
        this.id = id;
        this.name = name;
        this.buildingID = buildingID;
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
        return buildingID;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setBuildingID(UUID buildingID)
    {
        this.buildingID = buildingID;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public void setDeletedAt(LocalDateTime deletedAt)
    {
        this.deletedAt = deletedAt;
    }
}
