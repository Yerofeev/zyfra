package com.factory.entities;

import com.factory.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Room")
public class Room extends BaseEntity  {

    private String title;

    private Integer square;

    @ManyToOne
    @JoinColumn(name = "workshop_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Workshop workshop;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "room")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Tool> tools;

    public Room(){
    }

    public Room(String Title, Integer Square, Workshop workshop) {
        this.title = Title;
        this.square = Square;
        this.workshop = workshop;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSquare() {
        return square;
    }

    public void setSquare(Integer square) {
        this.square = square;
    }

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
}
