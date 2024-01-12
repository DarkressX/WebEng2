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
@Table(name="buildings")
public class Buildings
{
    @Id
    private UUID id;

    @NotBlank(message = "Building name is required")
    private String name;

    @NotBlank(message = "Building address is required")
    private String address;

    @JsonAlias("deleted_at")
    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    public Buildings(UUID id, String name, String address, LocalDateTime deletedAt)
    {
        this.id = id;
        this.name = name;
        this.address = address;
        this.deletedAt = deletedAt;
    }

    public Buildings(){}

    public UUID getId() {return id;}

    public String getName() {return name;}

    public String getAddress() {return address;}

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

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setDeletedAt(LocalDateTime deletedAt)
    {
        this.deletedAt = deletedAt;
    }
}
