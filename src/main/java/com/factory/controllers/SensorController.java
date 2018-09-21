package com.factory.controllers;

import com.factory.entities.Sensor;
import com.factory.entities.Tool;
import com.factory.repos.SensorRepo;
import com.factory.repos.ToolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class SensorController {

    @Autowired
    private SensorRepo sensorRepo;

    @Autowired
    private ToolRepo toolRepo;

    @ResponseBody
    @RequestMapping(value = "/sensor", method=GET)
    public List<Sensor> getSensor(@RequestParam Long toolId) {

        return sensorRepo.findByTool_ToolId(toolId);
    }

    @ResponseBody
    @RequestMapping(value = "/sensor/{id}", method=GET)
    public Map<String, Object> getSensor(@PathVariable("id") long id) {

        Map<String, Object> mapSensor = new HashMap<>();

        Sensor sensor = sensorRepo.findById(id).orElse(null);

        if (sensor == null){
            return mapSensor;
        }

        mapSensor.put("id", sensor.getSensorId());
        mapSensor.put("Docs", sensor.getDocs());
        mapSensor.put("Units", sensor.getUnits());
        mapSensor.put("Price", sensor.getPrice());

        return mapSensor;
    }

    @ResponseBody
    @RequestMapping(value = "/sensor", method=POST)
    public String addTool(@RequestParam String docs, @RequestParam Integer price, @RequestParam String units,  @RequestParam Long id) {
        Tool tool = toolRepo.findById(id).orElse(null);
        if (tool != null) {
            Sensor sensor = new Sensor(docs, units, price, tool);
            sensorRepo.save(sensor);
            return docs;
        }
        else {
            return "Tool with this Id does not exist!";
        }
    }

    @ResponseBody
    @RequestMapping(value = "/sensor/{id}", method=PUT)
    public ResponseEntity updateRool(@PathVariable("id") long id, @RequestParam String docs, @RequestParam Integer price, @RequestParam String units) {

        Sensor sensor = sensorRepo.findById(id).orElse(null);

        if (sensor == null){
            return ResponseEntity.notFound().build();
        }

        sensor.setDocs(docs);
        sensor.setPrice(price);
        sensor.setUnits(units);

        sensorRepo.save(sensor);

        return  ResponseEntity.ok().build();
    }

    @ResponseBody
    @RequestMapping(value = "/sensor/{id}", method=DELETE)
    public ResponseEntity deleteSensor(@PathVariable("id") long id) {

        Sensor sensor = sensorRepo.findById(id).orElse(null);

        if (sensor == null){
            return ResponseEntity.notFound().build();
        }

        sensorRepo.delete(sensor);

        return  ResponseEntity.ok().build();
    }
}