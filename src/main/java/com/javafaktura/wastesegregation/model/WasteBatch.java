package com.javafaktura.wastesegregation.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class WasteBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "wasteBatch", cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return "WasteBatch{" +
                "id=" + id +
                ", wastes=" + wastes +
                '}';
    }
}
