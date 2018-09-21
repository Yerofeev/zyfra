package com.factory.controllers;

import com.factory.entities.Tool;
import com.factory.repos.SensorRepo;
import com.factory.repos.ToolRepo;
import com.factory.entities.Sensor;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class SensorController {

    @Autowired
    private SensorRepo sensorRepo;

    @Autowired
    private ToolRepo toolRepo;

    @Autowired
    private EntityFields entityFields;

    @ResponseBody
    @RequestMapping(value = "/sensors", method=GET)
    public List<Sensor> getSensors(@RequestParam Long toolId) {
        return sensorRepo.findByTool_ToolId(toolId);
    }

    @ResponseBody
    @RequestMapping(value = "/sensor/{id}", method=GET)
    public Map<String, Object> getSensor(@PathVariable("id") Long id) throws IllegalAccessException, IntrospectionException, InvocationTargetException {

        Map<String, Object> mapSensor = new LinkedHashMap<>();

        Sensor sensor = sensorRepo.findById(id).orElse(null);

        if (sensor == null){
            return mapSensor;
        }
        mapSensor = entityFields.getEntityFields(sensor);

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
    public ResponseEntity updateRool(@PathVariable("id") Long id, @RequestParam String docs, @RequestParam Integer price, @RequestParam String units) {

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
    public ResponseEntity deleteSensor(@PathVariable("id") Long id) {

        Sensor sensor = sensorRepo.findById(id).orElse(null);

        if (sensor == null){
            return ResponseEntity.notFound().build();
        }

        sensorRepo.delete(sensor);

        return  ResponseEntity.ok().build();
    }
}