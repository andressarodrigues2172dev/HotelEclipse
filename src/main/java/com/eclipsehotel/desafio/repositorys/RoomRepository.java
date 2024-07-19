package com.eclipsehotel.desafio.repositorys;

import com.eclipsehotel.desafio.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
