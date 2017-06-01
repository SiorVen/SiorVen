package org.siorven.model;

import org.siorven.model.interfaces.IResourceContainer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name = "Distribution")
public abstract class Distribution implements IResourceContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distribution_id")
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "distribution_id")
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
}
