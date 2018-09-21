package com.factory.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sensorId;

    private String docs;

    private String units;

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    private Integer price;

    public Sensor(String docs, String units, Integer price, Tool tool) {
        this.docs = docs;
        this.units = units;
        this.price = price;
        this.tool = tool;
    }

    @ManyToOne
    @JoinColumn(name = "toolId", nullable = true, referencedColumnName = "toolId")
    @JsonIgnore
    private Tool tool;


    public Sensor(){
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

}
