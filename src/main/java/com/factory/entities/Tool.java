package com.factory.entities;

import com.factory.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Tool")
public class Tool extends BaseEntity {

    private String spec;

    private Integer numberOfSensors;

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "tool")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Sensor> sensors;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Room room;

    public Tool(){
    }

    public Tool(String Spec, Integer numberOfSensors, Room room) {
        this.spec = Spec;
        this.room = room;
        this.numberOfSensors = numberOfSensors;
    }

    public Integer getNumberOfSensors() {
        return numberOfSensors;
    }

    public void setNumberOfSensors(Integer numberOfSensors) {
        this.numberOfSensors = numberOfSensors;
    }

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

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

}
