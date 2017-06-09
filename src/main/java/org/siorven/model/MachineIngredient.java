package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Represents a ingredient stored in a machine
 */
@Entity
@Table(name = "machine_ingredient")
public class MachineIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_ingredient_id")
    private int id;

    @OneToOne
    private MachineResource resource;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int qty;

    @ManyToOne(fetch = FetchType.LAZY)
    private MachineProduct machineProduct;


    public MachineIngredient(MachineResource resource, int qty) {
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

    public MachineResource getResource() {
        return resource;
    }

    public void setResource(MachineResource resource) {
        this.resource = resource;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public MachineProduct getMachineProduct() {
        return machineProduct;
    }

    public void setMachineProduct(MachineProduct machineProduct) {
        this.machineProduct = machineProduct;
    }
}
