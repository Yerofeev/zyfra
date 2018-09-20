package com.factory.controllers;

import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class WorkshopController {

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private WorkshopRepo workshopRepo;

    @ResponseBody
    @RequestMapping(value = "/workshop", method=GET)
    public Iterable<Workshop> getWorkshop() {

        Iterable<Workshop> workshops = workshopRepo.findAll();

        return workshops;
    }

    @ResponseBody
    @RequestMapping(value = "/workshop", method=POST)
    public Long addWorkshop(@RequestParam String name) {
        Workshop workshop = new Workshop(name);

        workshopRepo.save(workshop);

        return workshop.getWorkshopId();
    }

}