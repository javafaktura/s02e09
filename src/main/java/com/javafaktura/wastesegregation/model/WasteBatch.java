package com.javafaktura.wastesegregation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity(name = "waste_batch")
public class WasteBatch {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy="wasteBatch", cascade = CascadeType.ALL)
    private List<Waste> wastes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Waste> getWastes() {
        return wastes;
    }

    public void setWastes(List<Waste> wastes) {
        this.wastes = wastes;
    }
}