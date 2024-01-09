package org.assets.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="rooms")
public class Rooms
{
    @Id
    private UUID id;

    @NotBlank(message = "Room name is required")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="storey_id", insertable = false, updatable = false)
    private Storeys storey;

    @JsonAlias("storey_id")
    @JsonProperty("storey_id")
    @Column(name = "storey_id")
    private UUID storeyID;

    @JsonAlias("deleted_at")
    private LocalDateTime deletedAt;

    public Rooms(UUID id, String name, UUID storeyID, LocalDateTime deletedAt)
    {
        this.id = id;
        this.name = name;
        this.storeyID = storeyID;
        this.deletedAt = deletedAt;
    }

    public Rooms(){}

    public UUID getId() {return id;}

    public String getName() {return name;}

    public UUID getStoreyID()
    {
        return storeyID;
    }

    public LocalDateTime getDeletedAt()
    {
        return deletedAt;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStoreyID(UUID storeyID)
    {
        this.storeyID = storeyID;
    }

    public void setDeletedAt(LocalDateTime deletedAt)
    {
        this.deletedAt = deletedAt;
    }
}
