package com.javafaktura.wastesegregation.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Waste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "waste_batch_id")
    @JsonIgnore
    private WasteBatch wasteBatch;

    // this is only to be shown in toString method
    @Column(name = "waste_batch_id", insertable = false, updatable = false)
    private Long wasteBatchId;

    private WasteType type;

    private BigDecimal weight;

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

    public WasteType getType() {
        return type;
    }

    public void setType(WasteType type) {
        this.type = type;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public Long getWasteBatchId() {
        return wasteBatchId;
    }

    public void setWasteBatchId(Long wasteBatchId) {
        this.wasteBatchId = wasteBatchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Waste)) return false;
        Waste waste = (Waste) o;
        return Objects.equals(getId(), waste.getId()) &&
                Objects.equals(getWasteBatch(), waste.getWasteBatch()) &&
                Objects.equals(getWasteBatchId(), waste.getWasteBatchId()) &&
                getType() == waste.getType() &&
                Objects.equals(getWeight(), waste.getWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWasteBatch(), getWasteBatchId(), getType(), getWeight());
    }

    @Override
    public String toString() {
        return "Waste{" +
                "id=" + id +
                ", wasteBatchId=" + wasteBatchId +
                ", type=" + type +
                ", weight=" + weight +
                '}';
    }
}
