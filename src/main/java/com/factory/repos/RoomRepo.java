package com.factory.repos;

import com.factory.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends JpaRepository<Room, Long> {
    List<Room> findByWorkshop_Id(Long Id);
    List<Room> findByTitle(String name);
}
