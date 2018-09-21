package com.factory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toolId;

    private String Spec;

    public Integer getNumberOfSensors() {
        return numberOfSensors;
    }

    public void setNumberOfSensors(Integer numberOfSensors) {
        this.numberOfSensors = numberOfSensors;
    }

    private Integer numberOfSensors;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "tool")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Sensor> sensors;

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = true, referencedColumnName = "roomId")
    @JsonIgnore
    private Room room;



    public Tool(String Spec, Integer numberOfSensors, Room room) {
        this.Spec = Spec;
        this.room = room;
        this.numberOfSensors = numberOfSensors;
    }

    public Tool(){
    }

    public Long getToolId() {
        return toolId;
    }

    public void setToolId(Long toolId) {
        this.toolId = toolId;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        this.Spec = spec;
    }

}
