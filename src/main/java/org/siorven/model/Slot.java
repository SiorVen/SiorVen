package org.siorven.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="slot")
public class Slot {

    @Id
    @Column(name="slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{NotEmpty.slot.name}")
    @Size(min = 3, max = 15, groups = {SpringFormGroup.class, SpringFormEditGroup.class}, message = "{Size.slot.name}")
    @Column(name="name")
    private String name;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class},  message = "{formatError.negativeNumber}")
    @Column(name="capacity")
    private int capacity;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class},  message = "{formatError.negativeNumber}")
    @Max(value = 5, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class},  message = "{formatError.tooBigNumber}")
    @Column(name="unit")
    private int Unit;

    public Slot(String name, int capacity, int unit) {
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
