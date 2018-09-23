package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
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
public class RoomController {

    private RoomRepo roomRepo;

    private WorkshopRepo workshopRepo;

    private EntityFields entityFields;

    @Autowired
    public RoomController( RoomRepo roomRepo, WorkshopRepo workshopRepo, EntityFields entityFields) {
        this.roomRepo = roomRepo;
        this.workshopRepo = workshopRepo;
        this.entityFields = entityFields;
    }

    @ResponseBody
    @RequestMapping(value = "/rooms", method=GET)
    public List<Room> getRooms(@RequestParam Long workshopId) {

        return roomRepo.findByWorkshop_Id(workshopId);
    }

    @ResponseBody
    @RequestMapping(value = "/room/full/{id}", method=GET)
    public Room getRoomWithChildren(@PathVariable("id") Long id) {

        Room room = roomRepo.findById(id).orElse(null);

        return room;
    }

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method=GET)
    public Map<String, Object> getRoom(@PathVariable("id") long id)
            throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        Map<String, Object> mapRoom = new LinkedHashMap<>();

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null){
            return mapRoom;
        }

        List<String> fieldsNotToBeFetched = Arrays.asList("tools", "workshop");
        mapRoom = entityFields.getEntityFields(room, fieldsNotToBeFetched);

        mapRoom.put("Tools", room.getTools().stream().map(tool->tool.getId()).collect(Collectors.toList()));

        return mapRoom;
    }

    @ResponseBody
    @RequestMapping(value = "/room", method=POST)
    public String addRoom(@RequestParam String title, @RequestParam Integer square, @RequestParam Long workshopId) {

        Workshop workshop = workshopRepo.findById(workshopId).orElse(null);

        if (workshop != null) {
            Room room = new Room(title, square, workshop);
            roomRepo.save(room);
            return title;
        }
        else {
            return "Workshop with this Id does not exist!";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method=PUT)
    public ResponseEntity updateRoom(@PathVariable("id") Long id,
                                     @RequestParam String title,
                                     @RequestParam Integer square) {

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null){
            return ResponseEntity.notFound().build();
        }

        if (square != null) {
            room.setSquare(square);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

        room.setTitle(title);
        roomRepo.save(room);

        return  ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/room/{id}", method=DELETE)
    public ResponseEntity deleteRoom(@PathVariable("id") Long id) {

        Room room = roomRepo.findById(id).orElse(null);

        if (room == null){
            return ResponseEntity.notFound().build();
        }

        roomRepo.delete(room);

        return  ResponseEntity.ok().build();
    }
}