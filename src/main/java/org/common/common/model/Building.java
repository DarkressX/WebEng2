package org.common.common.model;

import javax.persistence.*;

@Entity
@Table(name="building")
public class Building
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    public Building(Long id, String name, String address)
    {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Building(){}

    public Long getId() {return id;}

    public String getName() {return name;}

    public String getAddress() {return address;}


    public void setId(Long id)
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
