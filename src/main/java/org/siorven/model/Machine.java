package org.siorven.model;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")
    private MachineModel machineModel;

    @OneToMany(mappedBy = "machine", cascade = CascadeType.ALL)
    private List<MachineProduct> machineProductList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "resource_id")
    private List<MachineResource> machineResourceList;

    public Machine(String alias, MachineModel machineModel, List<MachineProduct> machineProductList, List<MachineResource> machineResourceList) {
        this.alias = alias;
        this.machineModel = machineModel;
        this.machineProductList = machineProductList;
        this.machineResourceList = machineResourceList;
    }

    public Machine(String alias, MachineModel machineModel) {
        this.alias = alias;
        this.machineModel = machineModel;
        this.machineProductList = new ArrayList<>();
        this.machineResourceList = new ArrayList<>();
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

    public MachineModel getMachineModel() {
        return machineModel;
    }

    public void setMachineModel(MachineModel machineModel) {
        this.machineModel = machineModel;
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

}
