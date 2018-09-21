package com.factory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private String Title;

    public Integer getSquare() {
        return Square;
    }

    public void setSquare(Integer square) {
        Square = square;
    }

    private Integer Square;

    @ManyToOne
    @JoinColumn(name = "WorkshopId", nullable = true, referencedColumnName = "WorkshopId")
    @JsonIgnore
    private Workshop workshop;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "room")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Tool> tools;

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public List<Tool> getTools() {
        return tools;
    }

    public void setTools(List<Tool> tools) {
        this.tools = tools;
    }

    public Room(String Title, Integer Square, Workshop workshop) {
        this.Title = Title;
        this.Square = Square;
        this.workshop = workshop;
    }

    public Room(){
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

}
