package org.siorven.controller.webint.forms;

/**
 * Model for the machine resource form
 */
public class MachineResourceForm {


    private String product;

    private int quantity;

    private int id;

    private int machineSlotId;

    private int machineId;

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
