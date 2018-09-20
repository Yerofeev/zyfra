package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class RoomController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private WorkshopRepo workshopRepo;

    @ResponseBody
    @RequestMapping(value = "/room", method=GET)
    public List<Room> getRoom(@RequestParam Long workshopId) {

        return roomRepo.findByWorkshop_WorkshopId(workshopId);
    }

    @ResponseBody
    @RequestMapping(value = "/room", method=POST)
    public String addRoom(@RequestParam String title, @RequestParam Long workshopId) {
        Workshop workshop = workshopRepo.findById(workshopId).orElse(null);
        if (workshop != null) {
            Room room = new Room(title, workshop);
            roomRepo.save(room);
            return title;
        }
        else {
            return "Workshop with this Id does not exist!";
        }
    }
}