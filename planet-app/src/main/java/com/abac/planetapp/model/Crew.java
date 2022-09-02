package com.abac.planetapp.model;

import org.springframework.data.domain.Page;

import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
public class Crew implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false, updatable = false)
    private Long id;
    private String captain;
    @OneToMany(mappedBy="crew",cascade = CascadeType.ALL)
    private List<Robot> robots;

    public Crew() {
    }

    public Crew(Long id) {
        this.id = id;
    }

    public Crew(Long id, String captain) {
        this.id = id;
        this.captain = captain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}
