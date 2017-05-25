package org.siorven.model;

import org.siorven.logic.Configuration;
import org.siorven.logic.Mapper;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */

@Entity
@Table(name = "machine")
public class Machine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_id")
    private int id;

    private String alias;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;

    @OneToMany(mappedBy = "machine")
    private List<MachineProduct> machineProductList;

    @OneToMany
    @JoinColumn(name = "resource_id")
    private List<MachineResource> machineResourceList;

    @Transient
    private Configuration configuration;

    @Transient
    private String record;

    @Transient
    private Mapper mapper;

    public Machine(String alias, Model model, List<MachineProduct> machineProductList, List<MachineResource> machineResourceList, Configuration configuration, String record, Mapper mapper) {
        this.alias = alias;
        this.model = model;
        this.machineProductList = machineProductList;
        this.machineResourceList = machineResourceList;
        this.configuration = configuration;
        this.record = record;
        this.mapper = mapper;
    }

    public Machine() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public List<MachineProduct> getMachineProductList() {
        return machineProductList;
    }

    public void setMachineProductList(List<MachineProduct> machineProductList) {
        this.machineProductList = machineProductList;
    }

    public List<MachineResource> getMachineResourceList() {
        return machineResourceList;
    }

    public void setMachineResourceList(List<MachineResource> machineResourceList) {
        this.machineResourceList = machineResourceList;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Mapper getMapper() {
        return mapper;
    }

    public void setMapper(Mapper mapper) {
        this.mapper = mapper;
    }
}
