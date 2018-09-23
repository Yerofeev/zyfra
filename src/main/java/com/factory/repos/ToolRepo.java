package com.factory.repos;

import com.factory.entities.Tool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToolRepo extends JpaRepository<Tool, Long> {
    List<Tool> findByRoom_Id(Long RoomId);
    List<Tool> findBySpec(String spec);
}
