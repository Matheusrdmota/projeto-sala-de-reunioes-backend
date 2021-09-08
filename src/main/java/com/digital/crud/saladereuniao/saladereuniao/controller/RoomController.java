package com.digital.crud.saladereuniao.saladereuniao.controller;

import com.digital.crud.saladereuniao.saladereuniao.exceptions.ResourceNotFoundException;
import com.digital.crud.saladereuniao.saladereuniao.models.Room;
import com.digital.crud.saladereuniao.saladereuniao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping()
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable(value = "id") Long roomId) throws ResourceNotFoundException{
        Room room = roomService.getRoomById(roomId);
        return ResponseEntity.ok().body(room);
    }
    @PostMapping()
    public Room createRoom (@Valid @RequestBody Room room){
        return roomService.createRoom(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable(value="id") Long roomId, @RequestBody Room roomDetails) throws ResourceNotFoundException{
        Room room = roomService.updateRoom(roomId, roomDetails);
        return ResponseEntity.ok().body(room);
    }
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable(value = "id") Long roomId) throws ResourceNotFoundException{
        roomService.deleteRoom(roomId);
    }
}
