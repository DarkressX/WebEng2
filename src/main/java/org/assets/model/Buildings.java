package org.assets.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name="buildings")
public class Buildings
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String address;

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
