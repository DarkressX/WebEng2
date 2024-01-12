package org.assets.controller;

import org.assets.model.Status;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@RequestMapping("/api/v2/assets/status")
@RestController
public class StatusController
{
    @GetMapping("")
    public Status getStatusInfo() {
        List<String> authors = new ArrayList<>();
        String apiVersion = "2";
        authors.add("Leon Schoch");
        authors.add("Erika Frank");
        return new Status(authors, apiVersion);
    }
}
