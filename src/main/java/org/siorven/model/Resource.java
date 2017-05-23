package org.siorven.model;

import javax.persistence.*;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="resource")
public class Resource
{
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="resourceType")
    private String resourceType;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Ingredient ingredient;

    public Resource(String id, String name, String resourceType) {
        this.id = id;
        this.name = name;
        this.resourceType = resourceType;
    }

    public Resource() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
