package org.siorven.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="Distribution")
public abstract class Distribution  implements IResourceContainer{

    @Id
    @Column(name="id")
    private String id;

    @Column(name="description")
    private String description;

    @OneToMany
    private List<Slot> slots;

    public Distribution(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Distribution() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
