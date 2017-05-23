package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by Andoni on 21/05/2017.
 */
@Entity
@Table(name="ingredient")
public class Ingredient {

    @Id
    @GeneratedValue
    @Column(name="ingredient_id")
    private int id;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int quantity;

    @OneToOne
    private Resource resource;

    public Ingredient(int id, Resource resource) {
        this.id = id;
        this.resource = resource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
