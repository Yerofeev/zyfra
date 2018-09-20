package com.factory.repos;

import com.factory.entities.Workshop;
import org.springframework.data.repository.CrudRepository;

public interface WorkshopRepo extends CrudRepository<Workshop, Long> {
}
