package org.siorven.model;

import javax.persistence.*;

/**
 * Represents the slot of a machine
 */
@Entity
@Table(name = "machine_slot")
public class MachineSlot {
    @Id
    @Column(name = "machine_slot_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Slot slot;

    @ManyToOne
    private Machine machine;

    public MachineSlot(Slot slot, Machine machine) {
        this.slot = slot;
        this.machine = machine;
    }

    public MachineSlot() {
        //empty constructor
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }
}
