package com.factory.repos;

import com.factory.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkshopRepo extends JpaRepository<Workshop, Long> {
}
