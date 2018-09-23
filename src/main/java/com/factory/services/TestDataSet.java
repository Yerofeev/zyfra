package com.factory.services;

import com.factory.entities.Room;
import com.factory.entities.Sensor;
import com.factory.entities.Tool;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.SensorRepo;
import com.factory.repos.ToolRepo;
import com.factory.repos.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
public class TestDataSet {

    private WorkshopRepo workshopRepo;

    private RoomRepo roomRepo;

    private ToolRepo toolRepo;

    private SensorRepo sensorRepo;

    public String name1 = "Alfa";
    public String name2 = "Beta";
    public Integer employeeCount1 = 100;
    public Integer employeeCount2 = 30;

    public String title1 = "Steel Casting";
    public String title2 = "Metal Casting";
    public Integer square1 = 1200;
    public Integer square2 = 1100;

    public String spec1 = "Spec1";
    public String spec2 = "Spec2";
    public Integer numberOfSensors1 = 7;
    public Integer numberOfSensors2 = 12;

    public String doc1 = "TXT";
    public String doc2 = "DOC";
    public String units1 = "Farengeit";
    public String units2 = "C";
    public Integer price1 = 1100;
    public Integer price2 = 777;


    @Autowired
    public TestDataSet(WorkshopRepo workshopRepo, RoomRepo roomRepo, ToolRepo toolRepo, SensorRepo sensorRepo) {
        this.workshopRepo = workshopRepo;
        this.sensorRepo = sensorRepo;
        this.roomRepo = roomRepo;
        this.toolRepo = toolRepo;
    }

    public void generateTestDataSet() {

        Workshop workshop1 = new Workshop(name1, employeeCount1);
        Workshop workshop2 = new Workshop(name2, employeeCount2);
        workshopRepo.saveAll(Arrays.asList(workshop1, workshop2));

        Room room1 = new Room(title1, square1, workshop1);
        Room room2 = new Room(title2, square2, workshop1);
        roomRepo.saveAll(Arrays.asList(room1, room2));

        Tool tool1 = new Tool(spec1, numberOfSensors1, room1);
        Tool tool2 = new Tool(spec2, numberOfSensors2, room2);
        toolRepo.saveAll(Arrays.asList(tool1, tool2));

        Sensor sensor1 = new Sensor(doc1, units1, price1, tool1);
        Sensor sensor2 = new Sensor(doc2, units2, price2, tool1);
        sensorRepo.saveAll(Arrays.asList(sensor1, sensor2));

    }
}