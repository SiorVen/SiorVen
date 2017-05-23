package org.siorven.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name = "machine_resource")
public class ResourceInMachine {

    @Id
    @GeneratedValue
    private String id;

    @ManyToOne
    private Resource resource;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "repositionDate")
    private Timestamp repositionDate;

    @Column(name = "estimatedCaducityDate")
    private Timestamp estimatedCaducityDate;

    @ManyToOne
    private Machine machine;

    @ManyToOne
    private Slot slot;

    public ResourceInMachine(Resource resource, int quantity, Timestamp repositionDate, Timestamp estimatedCaducityDate, Machine machine, Slot slotId) {
        this.resource = resource;
        this.quantity = quantity;
        this.repositionDate = repositionDate;
        this.estimatedCaducityDate = estimatedCaducityDate;
        this.machine = machine;
        this.slot = slot;
    }

    public ResourceInMachine() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public Resource getResourceId() {
        return resource;
    }

    public void setResourceId(Resource resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getRepositionDate() {
        return repositionDate;
    }

    public void setRepositionDate(Timestamp repositionDate) {
        this.repositionDate = repositionDate;
    }

    public Timestamp getEstimatedCaducityDate() {
        return estimatedCaducityDate;
    }

    public void setEstimatedCaducityDate(Timestamp estimatedCaducityDate) {
        this.estimatedCaducityDate = estimatedCaducityDate;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
