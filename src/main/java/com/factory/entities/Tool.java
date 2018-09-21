package com.factory.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long toolId;

    private String Spec;

    @OneToMany(orphanRemoval = true)
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
    private Room room;



    public Tool(String Spec, Room room) {
        this.Spec = Spec;
        this.room = room;
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
