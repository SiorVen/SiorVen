package org.siorven.model;

import javax.persistence.*;

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
