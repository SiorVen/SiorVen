package org.siorven.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a machine model
 */
@Entity
@Table(name = "model")
@XmlRootElement(name = "MachineModelXml")
public class MachineModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private int id;

    private String reference;

    private String manufacturer;


    @OneToMany(mappedBy = "machineModel", cascade = CascadeType.ALL)
    private List<Distribution> aviableDistributions;


    public MachineModel(String reference, String manufacturer, List<Distribution> aviableDistributions) {
        this.reference = reference;
        this.manufacturer = manufacturer;
        this.aviableDistributions = aviableDistributions;
    }

    public MachineModel() {
        aviableDistributions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public List<Distribution> getAviableDistributions() {
        return aviableDistributions;
    }

    public void setAviableDistributions(List<Distribution> aviableDistributions) {
        this.aviableDistributions = aviableDistributions;
    }
}
