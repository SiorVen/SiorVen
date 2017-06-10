package org.siorven.model;


import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a distribution based on compartments
 */
@Entity
@Table(name = "distribution")
public class CompartimentDistribution extends Distribution {

    public static final String SLOT_NAME = "distribution.compartiment.slotname";

    @Column(name = "numCompartiment")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int numCompartiment;

    public CompartimentDistribution(String description, int numCompartiment) {
        super(description);
        this.numCompartiment = numCompartiment;
    }

    public CompartimentDistribution() {
        //empty constructor
    }

    public int getNumCompartiment() {
        return numCompartiment;
    }

    public void setNumCompartiment(int numCompartiment) {
        this.numCompartiment = numCompartiment;
    }

    @Override
    public Slot findSlot(Map<String, Object> position) {
        Slot retSlot = null;
        String slotName = (String) position.get(SLOT_NAME);
        for (Slot slot : getSlots()) {
            if (slot.getName().equalsIgnoreCase(slotName)) {
                retSlot = slot;
                break;
            }
        }

        return retSlot;
    }

    @Override
    public Map<String, Class> getPositionParams() {
        Map<String, Class> map = new HashMap<>();
        map.put(SLOT_NAME, String.class);
        return map;
    }
}
