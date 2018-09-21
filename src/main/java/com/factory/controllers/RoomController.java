package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

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
    @RequestMapping(value = "/room/{id}", method=GET)
    public Map<String, Object> getRoom(@PathVariable("id") long id) {

        Map<String, Object> mapRoom = new HashMap<>();

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null){
            return mapRoom;
        }

        List<Long> tools = new ArrayList<>();
        room.getTools().forEach((tool)-> tools.add(tool.getToolId()));
        mapRoom.put("Rooms", tools);
        mapRoom.put("id", room.getRoomId());
        mapRoom.put("Name", room.getTitle());

        return mapRoom;
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

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method=PUT)
    public ResponseEntity updateRoom(@PathVariable("id") long id, @RequestParam String title) {

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null){
            return ResponseEntity.notFound().build();
        }

        room.setTitle(title);
        roomRepo.save(room);

        return  ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method=DELETE)
    public ResponseEntity deleteRoom(@PathVariable("id") long id) {

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null){
            return ResponseEntity.notFound().build();
        }

        roomRepo.delete(room);

        return  ResponseEntity.ok().build();
    }
}