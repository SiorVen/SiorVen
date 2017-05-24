package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name = "distribution")
public class CompartimentDistribution extends Distribution {

    @Column(name = "numCompartiment")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int numCompartiment;

    public CompartimentDistribution(String description, int numCompartiment) {
        super(description);
        this.numCompartiment = numCompartiment;
    }

    public int getNumCompartiment() {
        return numCompartiment;
    }

    public void setNumCompartiment(int numCompartiment) {
        this.numCompartiment = numCompartiment;
    }
}
