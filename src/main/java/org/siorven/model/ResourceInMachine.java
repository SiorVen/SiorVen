package org.siorven.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="ResourceInMachine")
public class ResourceInMachine {

    @ManyToOne
    @JoinColumn(name="resourceId")
    private int resourceId;

    @Column(name="quantity")
    private int quantity;

    @Column(name="repositionDate")
    private Timestamp repositionDate;

    @Column(name="estimatedCaducityDate")
    private Timestamp estimatedCaducityDate;

    @ManyToOne
    @JoinColumn(name="machineId")
    private int MachineId;

    @ManyToOne
    @JoinColumn(name="slotId")
    private int SlotId;

    @JoinColumn(name="id")
    @OneToMany(mappedBy = "admin")
    private List<Resource> resources;

    public ResourceInMachine(int resourceId, int quantity, Timestamp repositionDate, Timestamp estimatedCaducityDate, int machineId, int slotId, List<Resource> resources) {
        this.resourceId = resourceId;
        this.quantity = quantity;
        this.repositionDate = repositionDate;
        this.estimatedCaducityDate = estimatedCaducityDate;
        MachineId = machineId;
        SlotId = slotId;
        this.resources = resources;
    }

    public ResourceInMachine() {
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
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

    public int getMachineId() {
        return MachineId;
    }

    public void setMachineId(int machineId) {
        MachineId = machineId;
    }

    public int getSlotId() {
        return SlotId;
    }

    public void setSlotId(int slotId) {
        SlotId = slotId;
    }
}
