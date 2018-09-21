package com.factory.entities;

import com.factory.entities.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "Sensor")
public class Sensor extends BaseEntity {

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
    @JoinColumn(name = "tool_id", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Tool tool;


    public Sensor(){
    }

    public String getDocs() {
        return docs;
    }

    public void setDocs(String docs) {
        this.docs = docs;
    }

}
