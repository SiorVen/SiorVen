package org.siorven.model;

import org.siorven.model.interfaces.IResourceContainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class that defines a distribution
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Distribution implements IResourceContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distribution_id")
    private int id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "distribution_model_id")
    private MachineModel machineModel;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "distribution_slot_id")
    private List<Slot> slotList;

    public Distribution(String description) {
        this.description = description;
        slotList = new ArrayList<>();
    }

    public Distribution() {
        slotList = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Slot> getSlots() {
        return slotList;
    }

    public void setSlots(List<Slot> slots) {
        this.slotList = slots;
    }

    public MachineModel getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(MachineModel machineModel) {
        this.machineModel = machineModel;
    }

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<Slot> slotList) {
        this.slotList = slotList;
    }
}
