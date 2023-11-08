package org.assets.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name="buildings")
public class Buildings
{
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    private String address;

    public Buildings(UUID id, String name, String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Buildings(){}

    public UUID getId() {return id;}

    public String getName() {return name;}

    public String getAddress() {return address;}


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
}
