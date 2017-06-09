package org.siorven.controller.webint.forms;

/**
 * Model for the machine new machine Form
 */
public class MachineViewForm extends MachineEditForm {

    private String modelAlias;

    public MachineViewForm() {
        //empty constructor
    }

    public String getModelAlias() {
        return modelAlias;
    }

    public void setModelAlias(String modelAlias) {
        this.modelAlias = modelAlias;
    }
}
