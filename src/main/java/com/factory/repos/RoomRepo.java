package com.factory.repos;

import com.factory.entities.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepo extends CrudRepository<Room, Long> {
    List<Room> findByWorkshop_Id(Long Id);
}
