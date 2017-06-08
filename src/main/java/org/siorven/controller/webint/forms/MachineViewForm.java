package org.siorven.controller.webint.forms;

/**
 * Created by Andoni on 30/05/2017.
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
