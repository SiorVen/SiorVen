package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name = "machine_resource")
public class MachineResource {

    @Id
    @Column(name = "machine_resource_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Resource resource;

    @Column(name = "quantity")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int quantity;

    @Column(name = "repositionDate")
    @Future(groups = {PersistenceGroup.class}, message = "{formatError.DateFormat.NotPast}")
    private Timestamp repositionDate;

    @Column(name = "estimatedCaducityDate")
    @Future(groups = {PersistenceGroup.class}, message = "{formatError.DateFormat.NotPast}")
    private Timestamp estimatedCaducityDate;

    @ManyToOne
    private Slot slot;

    public MachineResource(Resource resource, int quantity, Timestamp repositionDate, Timestamp estimatedCaducityDate, Slot slot) {
        this.resource = resource;
        this.quantity = quantity;
        this.repositionDate = repositionDate;
        this.estimatedCaducityDate = estimatedCaducityDate;
        this.slot = slot;
    }

    public MachineResource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
