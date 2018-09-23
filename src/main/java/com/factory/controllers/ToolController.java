package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Tool;
import com.factory.repos.RoomRepo;
import com.factory.repos.ToolRepo;
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
public class ToolController {

    private RoomRepo roomRepo;

    private ToolRepo toolRepo;

    private EntityFields entityFields;

    @Autowired
    public ToolController( ToolRepo toolRepo, RoomRepo roomRepo, EntityFields entityFields) {
        this.roomRepo = roomRepo;
        this.toolRepo = toolRepo;
        this.entityFields = entityFields;
    }


    @ResponseBody
    @RequestMapping(value = "/tools", method=GET)
    public List<Tool> getTools(@RequestParam Long roomId) {

        return toolRepo.findByRoom_Id(roomId);
    }


    @ResponseBody
    @RequestMapping(value = "/tool/full/{id}", method=GET)
    public Tool getRoomWithChildren(@PathVariable("id") Long id) {

        Tool tool = toolRepo.findById(id).orElse(null);

        return tool;
    }


    @ResponseBody
    @RequestMapping(value = "/tool/{id}", method=GET)
    public Map<String, Object> getTool(@PathVariable("id") Long id) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        Map<String, Object> mapTool = new LinkedHashMap<>();

        Tool tool = toolRepo.findById(id).orElse(null);

        if (tool == null){
            return mapTool;
        }

        List<String> fieldsNotToBeFetched = Arrays.asList("sensors", "room");
        mapTool = entityFields.getEntityFields(tool, fieldsNotToBeFetched);

        mapTool.put("Sensors", tool.getSensors().stream().map(sensor->sensor.getId()).collect(Collectors.toList()));

        return mapTool;
    }

    @ResponseBody
    @RequestMapping(value = "/tool", method=POST)
    public String addTool(@RequestParam String spec, @RequestParam Integer numberOfSensors, @RequestParam Long roomId) {

        Room room = roomRepo.findById(roomId).orElse(null);

        if (room != null) {
            Tool tool = new Tool(spec, numberOfSensors, room);
            toolRepo.save(tool);

            return spec;
        }
        else {
            return "Room with this Id does not exist!";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/tool/{id}", method=PUT)
    public ResponseEntity updateRool(@PathVariable("id") Long id, @RequestParam String spec, @RequestParam Integer numberOfSensors) {

        Tool tool = toolRepo.findById(id).orElse(null);

        if (tool == null){
            return ResponseEntity.notFound().build();
        }

        if (numberOfSensors != null) {
            tool.setNumberOfSensors(numberOfSensors);
        }
        else {
            return ResponseEntity.badRequest().build();
        }

        tool.setSpec(spec);

        toolRepo.save(tool);

        return  ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/tool/{id}", method=DELETE)
    public ResponseEntity deleteTool(@PathVariable("id") Long id) {

        Tool tool = toolRepo.findById(id).orElse(null);

        if (tool == null){
            return ResponseEntity.notFound().build();
        }

        toolRepo.delete(tool);

        return  ResponseEntity.ok().build();
    }
}