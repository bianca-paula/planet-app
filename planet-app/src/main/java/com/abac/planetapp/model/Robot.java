package com.abac.planetapp.model;

import javax.persistence.*;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.List;

@Entity
public class Robot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false, updatable = false)
    private Long id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Crew crew;

    public Robot() {
    }

    public Robot(Long id, String name, Crew crew) {
        this.id = id;
        this.name = name;
        this.crew=crew;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
