package com.factory.repos;

import com.factory.entities.Tool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepo extends CrudRepository<Tool, Long> {
    List<Tool> findByRoom_Id(Long RoomId);
}
