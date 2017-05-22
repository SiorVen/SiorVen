package org.siorven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="Slot")
public class Slot {

    @Id
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="capacity")
    private int capacity;

    @Column(name="unit")
    private int Unit;

    public Slot(int id, String name, int capacity, int unit) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        Unit = unit;
    }

    public Slot() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getUnit() {
        return Unit;
    }

    public void setUnit(int unit) {
        Unit = unit;
    }
}
