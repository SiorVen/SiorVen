package org.siorven.model;

import javax.persistence.*;

/**
 * Represents a resource
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
    private ResourceType resourceType;


    public Resource(String name, ResourceType resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }

    public Resource() {
        //empty constructor
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

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

}
