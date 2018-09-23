package com.factory.controllers;

import com.factory.services.TestDataSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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