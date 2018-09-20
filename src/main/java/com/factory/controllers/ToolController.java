package com.factory.controllers;

import com.factory.entities.Room;
import com.factory.entities.Tool;
import com.factory.entities.Workshop;
import com.factory.repos.RoomRepo;
import com.factory.repos.ToolRepo;
import com.factory.repos.ToolRepo;
import com.factory.repos.WorkshopRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    @RequestMapping(value = "/tool", method=POST)
    public String addTool(@RequestParam String spec, @RequestParam Long roomId) {
        Room room = roomRepo.findById(roomId).orElse(null);
        if (room != null) {
            Tool tool = new Tool(spec, room);
            toolRepo.save(tool);
            return spec;
        }
        else {
            return "Room with this Id does not exist!";
        }
    }
}