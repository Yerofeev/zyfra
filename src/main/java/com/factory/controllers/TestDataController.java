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
import com.factory.services.TestDataSet;
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

    private TestDataSet testDataSet;

    public TestDataController(TestDataSet testDataSet) {
        this.testDataSet = testDataSet;
    }

    @ResponseBody
    @RequestMapping(value = "/testdata", method=GET)
    public ResponseEntity generateTestDataSet() {

        testDataSet.generateTestDataSet();

        return  ResponseEntity.ok().body("DataSet successfully created");
    }
}