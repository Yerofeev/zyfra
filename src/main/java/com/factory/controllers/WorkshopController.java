package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class WorkshopController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private WorkshopRepo workshopRepo;

    @ResponseBody
    @RequestMapping(value = "/workshop", method=GET)
    public Iterable<Workshop> getWorkshops() {

        Iterable<Workshop> workshops = workshopRepo.findAll();

        return workshops;
    }

    @ResponseBody
    @RequestMapping(value = "/workshop/{id}", method=GET)
    public Map<String, Object> getWorkshop(@PathVariable("id") long id) {

        Map<String, Object> mapWorkshop = new HashMap<>();

        Workshop workshop = workshopRepo.findById(id).orElse(null);

        if (workshop == null){
            return mapWorkshop;
        }

        List<Long> rooms = new ArrayList<>();
        workshop.getRooms().forEach((room)-> rooms.add(room.getRoomId()));
        mapWorkshop.put("Rooms", rooms);
        mapWorkshop.put("id", workshop.getWorkshopId());
        mapWorkshop.put("Name", workshop.getName());

        return mapWorkshop;
    }

    @ResponseBody
    @RequestMapping(value = "/workshop", method=POST)
    public Long addWorkshop(@RequestParam String name, @RequestParam Integer count) {

        Workshop workshop = new Workshop(name, count);

        workshopRepo.save(workshop);

        return workshop.getWorkshopId();
    }

    @ResponseBody
    @RequestMapping(value = "/workshop/{id}", method=PUT)
    public ResponseEntity updateWorkshop(@PathVariable("id") long id, @RequestParam String name) {

        Workshop workshop = workshopRepo.findById(id).orElse(null);

        if (workshop == null){
            return ResponseEntity.notFound().build();
        }

        workshop.setName(name);
        workshopRepo.save(workshop);

        return  ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/workshop/{id}", method=DELETE)
    public ResponseEntity deleteWorkshop(@PathVariable("id") long id) {

        Workshop workshop = workshopRepo.findById(id).orElse(null);

        if (workshop == null){
            return ResponseEntity.notFound().build();
        }

        workshopRepo.save(workshop);

        return  ResponseEntity.ok().build();
    }

}