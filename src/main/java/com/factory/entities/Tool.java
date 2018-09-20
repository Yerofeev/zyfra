package com.factory.entities;

import javax.persistence.*;

@Entity
public class Tool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String Spec;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    private Room room;

    public Tool(String Spec, Room room) {
        this.Spec = Spec;
        this.room = room;
    }

    public Tool(){
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getSpec() {
        return Spec;
    }

    public void setSpec(String spec) {
        this.Spec = spec;
    }

}
