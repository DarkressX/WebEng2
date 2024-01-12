package org.assets.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Status
{
    private List<String> name;

    @JsonProperty("api_version")
    private String apiVersion;

    public List<String> getName()
    {
        return name;
    }

    public Status(List<String> name, String apiVersion)
    {
        this.name = name;
        this.apiVersion = apiVersion;
    }

    public void setName(List<String> name)
    {
        this.name = name;
    }

    public String getApiVersion()
    {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion)
    {
        this.apiVersion = apiVersion;
    }
}
