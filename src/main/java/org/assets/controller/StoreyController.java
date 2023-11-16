package org.assets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assets.model.Storeys;
import org.assets.service.StoreyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v2/assets/storeys")
public class StoreyController
{
    private final StoreyService storeyService;
    public StoreyController(StoreyService storeyService) {
        this.storeyService = storeyService;
    }

    @GetMapping("")
    public ResponseEntity<List<Storeys>> getStoreys(@Valid @RequestParam(required = false, defaultValue = "false") Boolean include_deleted, @RequestParam UUID building_id)
    {
        return ResponseEntity.ok().body(storeyService.getAllStoreys(include_deleted, building_id));
    }

    @PostMapping("")
    public ResponseEntity<Storeys> saveStorey(@RequestBody Storeys storeys) {
        Storeys newStorey;
        try {
            newStorey = storeyService.saveStorey(storeys);
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newStorey.getId()).toUri();
        return ResponseEntity.created(uri).body(newStorey);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Storeys> getStoreyByID(@PathVariable UUID id) {
        if (storeyService.getStoreyByID(id) != null) {
            return ResponseEntity.ok().body(storeyService.getStoreyByID(id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Storeys> deleteStorey(@PathVariable UUID id) {
        try {
            storeyService.deleteStorey(id);
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build(); //Storey does not exist or already deleted
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Storeys> putStorey(@Valid @PathVariable UUID id,
                                                 @RequestBody Map<String, Object> requestBody) {
        boolean restore = false;
        Storeys storeys;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            storeys = objectMapper.convertValue(requestBody, Storeys.class);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }

        if(requestBody.containsKey("deleted_at") && requestBody.get("deleted_at") == null) {
            restore = true;
        }
        if (storeyService.getStoreyByID(id) != null) {
            try {
                return ResponseEntity.ok().body(storeyService.updateStoreyByID(id, storeys, restore));
            }
            catch(UnsupportedOperationException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
        storeys.setId(id);
        return ResponseEntity.created(uri).body(storeyService.updateStoreyByID(id, storeys, false));
    }
}
