package org.siorven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="distribution")
public class CompartimentDistribution extends Distribution{

    @Column(name="numCompartiment")
    private int numCompartiment;

    public CompartimentDistribution(String id, String description, int numCompartiment) {
        super(id, description);
        this.numCompartiment = numCompartiment;
    }

    public CompartimentDistribution() {
    }

    public int getNumCompartiment() {
        return numCompartiment;
    }

    public void setNumCompartiment(int numCompartiment) {
        this.numCompartiment = numCompartiment;
    }
}
