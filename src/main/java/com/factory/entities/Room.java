package com.factory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    private String Title;

    @ManyToOne
    @JoinColumn(name = "WorkshopId", nullable = true, referencedColumnName = "WorkshopId")
    @JsonIgnore
    private Workshop workshop;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "room")
    @Fetch(value = FetchMode.SUBSELECT)
    private Set<Tool> tools;

    public Workshop getWorkshop() {
        return workshop;
    }

    public void setWorkshop(Workshop workshop) {
        this.workshop = workshop;
    }

    public Set<Tool> getTools() {
        return tools;
    }

    public void setTools(Set<Tool> tools) {
        this.tools = tools;
    }

    public Room(String Title, Workshop workshop) {
        this.Title = Title;
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
