package org.siorven.model;

import javax.persistence.*;

/**
 * Created by Andoni on 21/05/2017.
 */
@Entity
@Table(name="ingredient")
public class Ingredient {

    @Id
    @Column(name="ingredient_id")
    private int id;

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
