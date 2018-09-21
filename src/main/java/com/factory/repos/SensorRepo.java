package com.factory.repos;

import com.factory.entities.Sensor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepo extends CrudRepository<Sensor, Long> {
    List<Sensor> findByTool_Id(Long ToolId);
}
