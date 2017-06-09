package org.siorven.controller.webint.forms;

/**
 * Created by Andoni on 04/06/2017.
 */
public class MachineResourceForm {


    String product;

    int quantity;

    int id;

    int machineSlotId;

    int machineId;

    public MachineResourceForm() {
        //empty constructor
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMachineSlotId() {
        return machineSlotId;
    }

    public void setMachineSlotId(int machineSlotId) {
        this.machineSlotId = machineSlotId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}
