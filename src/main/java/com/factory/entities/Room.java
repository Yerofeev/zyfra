package com.factory.entities;

import javax.persistence.*;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    private String Title;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "WorkshopId", referencedColumnName = "WorkshopId")
    private Workshop workshop;

    public Room(String Title, Workshop workshop) {
        this.Title = Title;
        this.workshop = workshop;
    }

    public Room(){
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

}
