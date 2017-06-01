package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;

import javax.persistence.*;
import javax.validation.constraints.Future;
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
    private int quantity;

    @Column(name = "repositionDate")
    @Future(groups = {PersistenceGroup.class}, message = "{formatError.DateFormat.NotPast}")
    private Timestamp repositionDate;

    @Column(name = "estimatedCaducityDate")
    @Future(groups = {PersistenceGroup.class}, message = "{formatError.DateFormat.NotPast}")
    private Timestamp estimatedCaducityDate;

    @OneToOne
    private MachineSlot machineSlot;

    public MachineResource(Resource resource, int quantity, Timestamp repositionDate, Timestamp estimatedCaducityDate, MachineSlot machineSlot) {
        this.resource = resource;
        this.quantity = quantity;
        this.repositionDate = repositionDate;
        this.estimatedCaducityDate = estimatedCaducityDate;
        this.machineSlot = machineSlot;
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

    public MachineSlot getMachineSlot() {
        return machineSlot;
    }

    public void setMachineSlot(MachineSlot machineSlot) {
        this.machineSlot = machineSlot;
    }
}
