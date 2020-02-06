package com.javafaktura.wastesegregation.model;

import javax.persistence.*;

@Entity(name = "waste")
public class Waste {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "waste_batch_id")
    private WasteBatch wasteBatch;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WasteBatch getWasteBatch() {
        return wasteBatch;
    }

    public void setWasteBatch(WasteBatch wasteBatch) {
        this.wasteBatch = wasteBatch;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
