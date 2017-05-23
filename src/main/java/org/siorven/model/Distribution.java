package org.siorven.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="Distribution")
public class Distribution {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="description")
    private String description;

    @OneToMany
    private List<Slot> slotList;

    public Distribution(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Distribution() {
    }
}
