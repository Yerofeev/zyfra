package com.factory.entities;

import com.factory.entities.base.BaseEntity;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Workshop")
public class Workshop extends BaseEntity {

    private String name;

    private Integer employeeCount;

    public Workshop(String name, Integer employeeCount) {
        this.name = name;
        this.employeeCount = employeeCount;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "workshop")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Room> rooms;

    public Workshop(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }


}
