package org.assets.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Status
{
    private List<String> authors;

    @JsonProperty("api_version")
    private String apiVersion;

    public List<String> getAuthors()
    {
        return authors;
    }

    public Status(List<String> authors, String apiVersion)
    {
        this.authors = authors;
        this.apiVersion = apiVersion;
    }

    public void setAuthors(List<String> authors)
    {
        this.authors = authors;
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
