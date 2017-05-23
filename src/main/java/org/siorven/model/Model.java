package org.siorven.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="model")
public class Model {

    @Id
    @GeneratedValue
    @Column(name="model_id")
    private String id;

    private String description;

    private String manufacturer;

    @OneToMany
    @JoinColumn(name="model_id")
    private List<ProductType> aviableProductTypes;

    @OneToMany
    @JoinColumn(name="model_id")
    private List<Distribution> aviableDistributions;


    public Model(String id, String description, String manufacturer, List<ProductType> aviableProductTypes, List<Distribution> aviableDistributions) {
        this.id = id;
        this.description = description;
        this.manufacturer = manufacturer;
        this.aviableProductTypes = aviableProductTypes;
        this.aviableDistributions = aviableDistributions;
    }

    public Model() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<ProductType> getAviableProductTypes() {
        return aviableProductTypes;
    }

    public void setAviableProductTypes(List<ProductType> aviableProductTypes) {
        this.aviableProductTypes = aviableProductTypes;
    }

    public List<Distribution> getAviableDistributions() {
        return aviableDistributions;
    }

    public void setAviableDistributions(List<Distribution> aviableDistributions) {
        this.aviableDistributions = aviableDistributions;
    }
}
