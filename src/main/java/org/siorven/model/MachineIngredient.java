package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by Andoni on 19/05/2017.
 */
@Entity
@Table(name="machine_ingredient")
public class MachineIngredient {

    @Id
    @GeneratedValue
    @Column(name="machine_ingredient_id")
    private int id;

    @OneToOne
    private ResourceInMachine resource;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int qty;


    public MachineIngredient(ResourceInMachine resource, int qty) {
        this.resource = resource;
        this.qty = qty;
    }

    public MachineIngredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ResourceInMachine getResource() {
        return resource;
    }

    public void setResource(ResourceInMachine resource) {
        this.resource = resource;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
