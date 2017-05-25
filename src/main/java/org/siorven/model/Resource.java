package org.siorven.model;

import javax.persistence.*;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name = "resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "resourceType")
    private String resourceType;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Ingredient ingredient;

    public Resource(String name, String resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }

    public Resource() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
