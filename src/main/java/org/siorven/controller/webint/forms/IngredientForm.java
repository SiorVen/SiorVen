package org.siorven.controller.webint.forms;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Model for the new Ingredient form
 */
public class IngredientForm {

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @Min(value = 1, message = "{error.ingredient.minQty}")
    private Integer qty;

    @NotNull(message = "{confAlg.CantBeEmpty}")
    @NotEmpty(message = "{error.ingredient.resourceEmpty}")
    private String name;

    public IngredientForm() {
        qty = 0;
        name = "";
    }


    public Integer getQty() { return qty; }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
