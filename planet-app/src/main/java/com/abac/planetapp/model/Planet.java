package com.abac.planetapp.model;

import com.abac.planetapp.exception.CrewNotFoundException;
import com.abac.planetapp.repository.CrewRepository;
import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Check(constraints = "status='OK' OR status='!OK' OR status='En route' OR status = 'TODO'")
public class Planet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    @Pattern(regexp = "(http(s?):)([\\/|.|\\w|\\s|-])*\\.(?:jpg|gif|png|jpeg)")
    private String image;

    @NotNull
    @NotBlank
    private String status;

    @ManyToOne
    @NotNull
    private Crew crew;

    @Transient
    CrewRepository crewRepository;
    public Planet() {
    }

    public Planet(Long id, String name, String image, String status, Crew crew) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.crew = crew;
    }



    public Planet(Long id, String name, String image, String status, String crew) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.status = status;
        this.crew = crewRepository.findCrewById(Long.parseLong(crew)).orElseThrow(RuntimeException::new);
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Crew getCrew() {
        return crew;
    }

    public void setCrew(Crew crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Planet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", status='" + status + '\'' +
                ", crew='" + crew + '\'' +
                '}';
    }
}
