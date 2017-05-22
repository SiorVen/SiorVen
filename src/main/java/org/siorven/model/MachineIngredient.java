package org.siorven.model;

import javax.persistence.Id;

/**
 * Created by Andoni on 19/05/2017.
 */
public class MachineIngredient {

    @Id
    private int id;

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
