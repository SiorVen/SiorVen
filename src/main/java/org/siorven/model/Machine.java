package org.siorven.model;

import org.siorven.logic.Configuration;
import org.siorven.logic.Mapper;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 17/05/2017.
 */

@Entity
@Table(name="machine")
public class Machine {


    @Id
    private String id;

    private String alias;

    @ManyToOne
    @JoinColumn(name="model_id")
    private Model model;

    @ManyToOne
    @JoinColumn(name="machine_Product_list_id")
    private List<MachineProduct> machineProductList;

    @ManyToOne
    @JoinColumn(name="machine_Product_list_id")
    private List<ResourceInMachine> machineResourceList;

    private Configuration configuration;

    private String record;

    private Mapper mapper;

    public Machine(String id, String alias, Model model, List<MachineProduct> machineProductList, List<ResourceInMachine> machineResourceList, Configuration configuration, String record, Mapper mapper) {
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<ResourceInMachine> getMachineResourceList() {
        return machineResourceList;
    }

    public void setMachineResourceList(List<ResourceInMachine> machineResourceList) {
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
