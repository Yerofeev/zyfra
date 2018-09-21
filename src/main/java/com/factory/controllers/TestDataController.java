package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Sensor;
import com.factory.entities.Tool;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.SensorRepo;
import com.factory.repos.ToolRepo;
import com.factory.repos.WorkshopRepo;
import com.factory.services.EntityFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class TestDataController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private WorkshopRepo workshopRepo;

    @Autowired
    private ToolRepo toolRepo;

    @Autowired
    private SensorRepo sensorRepo;

    @ResponseBody
    @RequestMapping(value = "/testdata", method=GET)
    public ResponseEntity generateTestDataSet() {

        Workshop workshop1 = new Workshop("ALFA", 100);
        Workshop workshop2 = new Workshop("Beta", 30);
        workshopRepo.saveAll(Arrays.asList(workshop1, workshop2));

        Room room1 = new Room("Steel Casting", 1200, workshop1);
        Room room2 = new Room("Metal Casting", 1100, workshop1);
        roomRepo.saveAll(Arrays.asList(room1, room2));

        Tool tool1 = new Tool("Spec1", 7, room1);
        Tool tool2 = new Tool("Spec2", 12, room2);
        toolRepo.saveAll(Arrays.asList(tool1, tool2));

        Sensor sensor1 = new Sensor("TXT", "Farengeit", 1100, tool1);
        Sensor sensor2 = new Sensor("DOC", "C", 777, tool1);
        sensorRepo.saveAll(Arrays.asList(sensor1, sensor2));

        return  ResponseEntity.ok().body("DataSet successfully created");
    }
}