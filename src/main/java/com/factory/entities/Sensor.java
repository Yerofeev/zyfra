package com.factory.entities;

import javax.persistence.*;

@Entity
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sensorId;

    private String Docs;

    private String Units;

    public String getUnits() {
        return Units;
    }

    public void setUnits(String units) {
        Units = units;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    private Integer Price;

    public Sensor(String docs, String units, Integer price, Tool tool) {
        Docs = docs;
        Units = units;
        Price = price;
        this.tool = tool;
    }

    @ManyToOne
    @JoinColumn(name = "toolId", nullable = true, referencedColumnName = "toolId")
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
        return Docs;
    }

    public void setDocs(String docs) {
        this.Docs = docs;
    }

}
