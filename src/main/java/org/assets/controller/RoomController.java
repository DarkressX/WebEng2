package org.assets.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assets.model.Rooms;
import org.assets.service.RoomService;
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
@RequestMapping("/api/v2/assets/rooms")
public class RoomController
{
    private final RoomService roomService;
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("")
    public ResponseEntity<List<Rooms>> getRooms(@Valid @RequestParam(required = false, defaultValue = "false") Boolean include_deleted, @RequestParam UUID storey_id)
    {
        return ResponseEntity.ok().body(roomService.getAllRooms(include_deleted, storey_id));
    }

    @PostMapping("")
    public ResponseEntity<Rooms> saveRoom(@Valid @RequestBody Rooms rooms) {
        Rooms newRoom;
        try {
            newRoom = roomService.saveRoom(rooms);
        }
        catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(newRoom.getId()).toUri();
        return ResponseEntity.created(uri).body(newRoom);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rooms> getRoomByID(@PathVariable UUID id) {
        if (roomService.getRoomByID(id) != null) {
            return ResponseEntity.ok().body(roomService.getRoomByID(id));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Rooms> deleteRoom(@PathVariable UUID id) {
        try {
            roomService.deleteRoom(id);
        } catch(NoSuchElementException e) {
            return ResponseEntity.notFound().build(); //Room does not exist or already deleted
        }
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rooms> putRoom(@Valid @PathVariable UUID id,
                                                 @RequestBody Map<String, Object> requestBody) {
        boolean restore = false;
        Rooms rooms;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            rooms = objectMapper.convertValue(requestBody, Rooms.class);
        }
        catch(Exception e) {
            return ResponseEntity.badRequest().build();
        }
        if(requestBody.containsKey("deleted_at") && requestBody.get("deleted_at") == null) {
            restore = true;
        }

        try {
            return ResponseEntity.ok().body(roomService.updateRoomByID(id, rooms, restore));
        }
        catch(UnsupportedOperationException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        catch(NoSuchElementException e) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
            return ResponseEntity.created(uri).body(roomService.saveRoomByID(id, rooms));
        }
    }
}
