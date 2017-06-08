package org.siorven.controller.webint.forms;

/**
 * Created by Andoni on 30/05/2017.
 */
public class MachineEditForm {

    private int machineModelId;

    private String alias;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MachineEditForm() {
        //empty constructor
    }

    public int getMachineModelId() {
        return machineModelId;
    }

    public void setMachineModelId(int machineModelId) {
        this.machineModelId = machineModelId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

}
