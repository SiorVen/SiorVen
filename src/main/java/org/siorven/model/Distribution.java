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
    @GeneratedValue
    @Column(name="distribution_id")
    private String id;

    @Column(name="description")
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="distribution_id")
    private List<Slot> slotList;

    public Distribution(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public Distribution() {
    }
}
