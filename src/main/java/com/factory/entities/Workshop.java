package com.factory.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workshopId;

    private String Name;

    private Integer EmployeeCount;

    public Workshop(String name, Integer employeeCount) {
        Name = name;
        EmployeeCount = employeeCount;

    }

    public Integer getEmployeeCount() {
        return EmployeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        EmployeeCount = employeeCount;
    }



    @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "workshop")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Room> rooms;

    public Workshop(){
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public Long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(Long workshopId) {
        this.workshopId = workshopId;
    }

}
