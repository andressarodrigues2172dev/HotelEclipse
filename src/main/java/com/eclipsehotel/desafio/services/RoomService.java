package com.eclipsehotel.desafio.services;

import com.eclipsehotel.desafio.models.Room;
import com.eclipsehotel.desafio.models.RoomStatus;
import com.eclipsehotel.desafio.repositorys.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room room) {
        if (roomRepository.existsById(id)) {
            room.setId(id);
            return roomRepository.save(room);
        } else {
            return null;
        }
    }

    public void deleteRoom(Long id) {
        if (roomRepository.existsById(id)) {
            roomRepository.deleteById(id);
        }
    }

    public List<Room> getOccupiedRooms() {
        return roomRepository.findByStatus(RoomStatus.OCCUPIED);
    }

    public Room updateRoomStatus(Long id, RoomStatus status) {
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null) {
            room.setStatus(status);
            return roomRepository.save(room);
        }
        return null; // ou lançar uma exceção se preferir
    }
}
