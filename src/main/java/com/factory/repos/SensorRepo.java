package com.factory.repos;

import com.factory.entities.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepo extends JpaRepository<Sensor, Long> {
    List<Sensor> findByTool_Id(Long ToolId);
}
