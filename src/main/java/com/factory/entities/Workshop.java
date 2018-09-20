package com.factory.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long workshopId;

    private String Name;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="workshop",cascade = CascadeType.ALL)
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = false)
    private List<Room> rooms;

    public Workshop(){
    }

    public Workshop(String name) {
        Name = name;
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
