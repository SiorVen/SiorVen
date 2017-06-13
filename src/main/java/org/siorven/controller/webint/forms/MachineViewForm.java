package org.siorven.controller.webint.forms;

import javax.validation.constraints.Size;

/**
 * Model for the machine new machine Form
 */
public class MachineViewForm extends MachineEditForm {

    @Size(max = 250)
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
