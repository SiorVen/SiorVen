package org.siorven.controller.webint.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * Created by ander on 30/05/2017.
 */
public class Ingredientform {

    @Min(value = 1, message = "error.ingredient.minQty")
    private int qty;
    @NotEmpty(message = "error.ingredient.resourceEmpty")
    private String name;

    public Ingredientform() {
    }


    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
