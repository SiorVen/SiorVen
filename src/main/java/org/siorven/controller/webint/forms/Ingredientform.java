package org.siorven.controller.webint.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

/**
 * Model for the new Ingredient form
 */
public class Ingredientform {

    @Min(value = 1, message = "{error.ingredient.minQty}")
    private int qty;
    @NotEmpty(message = "{error.ingredient.resourceEmpty}")
    private String name;

    public Ingredientform() {
        //empty constructor
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
