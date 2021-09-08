package com.digital.crud.saladereuniao.saladereuniao.service;

import com.digital.crud.saladereuniao.saladereuniao.exceptions.ResourceNotFoundException;
import com.digital.crud.saladereuniao.saladereuniao.models.Room;
import com.digital.crud.saladereuniao.saladereuniao.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) throws ResourceNotFoundException{
        return verifyIfExists(id);
    }

    public Room createRoom(Room roomToBeCreated){
        return roomRepository.save(roomToBeCreated);
    }

    public Room updateRoom(Long id, Room room) throws ResourceNotFoundException{
        Room roomToBeUpdated = verifyIfExists(id);

        roomToBeUpdated.setName(room.getName());
        roomToBeUpdated.setDate(room.getDate());
        roomToBeUpdated.setStartHour(room.getStartHour());
        roomToBeUpdated.setEndHour(room.getEndHour());

        return roomRepository.save(roomToBeUpdated);
    }

    public void deleteRoom(Long id) throws ResourceNotFoundException{
        verifyIfExists(id);
        roomRepository.deleteById(id);
    }

    private Room verifyIfExists(Long id) throws ResourceNotFoundException{
        Room roomToBeVerified = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with id: " + id + " not found"));

        return roomToBeVerified;
    }
}
