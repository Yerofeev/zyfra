package com.factory.repos;

import com.factory.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkshopRepo extends JpaRepository<Workshop, Long> {
    List<Workshop> findByName(String name);
}
