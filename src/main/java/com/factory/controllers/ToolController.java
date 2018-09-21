package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Tool;
import com.factory.repos.RoomRepo;
import com.factory.repos.ToolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class ToolController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private ToolRepo toolRepo;

    @ResponseBody
    @RequestMapping(value = "/tool", method=GET)
    public List<Tool> getTool(@RequestParam Long roomId) {

        return toolRepo.findByRoom_RoomId(roomId);
    }

    @ResponseBody
    @RequestMapping(value = "/tool/{id}", method=GET)
    public Map<String, Object> getTool(@PathVariable("id") long id) {

        Map<String, Object> mapTool = new LinkedHashMap<>();

        Tool tool = toolRepo.findById(id).orElse(null);

        if (tool == null){
            return mapTool;
        }

        mapTool.put("id", tool.getToolId());
        mapTool.put("Name", tool.getSpec());
        mapTool.put("NumberOfSensors", tool.getNumberOfSensors());

        List<Long> sensors = new ArrayList<>();
        tool.getSensors().forEach((sensor)-> sensors.add(sensor.getSensorId()));
        mapTool.put("Sensors", sensors);

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
    public ResponseEntity updateRool(@PathVariable("id") long id, @RequestParam String spec) {

        Tool tool = toolRepo.findById(id).orElse(null);

        if (tool == null){
            return ResponseEntity.notFound().build();
        }

        tool.setSpec(spec);

        toolRepo.save(tool);

        return  ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/tool/{id}", method=DELETE)
    public ResponseEntity deleteTool(@PathVariable("id") long id) {

        Tool tool = toolRepo.findById(id).orElse(null);

        if (tool == null){
            return ResponseEntity.notFound().build();
        }

        toolRepo.delete(tool);

        return  ResponseEntity.ok().build();
    }
}