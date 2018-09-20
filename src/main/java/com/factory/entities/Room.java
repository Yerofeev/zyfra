package com.factory.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomId;

    private String Title;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "WorkshopId", referencedColumnName = "WorkshopId")
    private Workshop workshop;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="room",cascade = CascadeType.ALL)
    private List<Tool> tools;

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }
/*
    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }*/



    public Room(String Title, Workshop workshop) {
        this.Title = Title;
        this.workshop = workshop;
    }

    public Room(){
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

}
