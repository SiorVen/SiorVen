package org.siorven.model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Andoni on 19/05/2017.
 */


public class MachineProduct {

    @Id
    int id;

    private Product product;

    private float price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ingredient_in_machine", joinColumns = {
            @JoinColumn(name = "ingredient_machine_id", nullable = false, updatable = false) })
    private ArrayList<MachineIngredient> recipe;


    public MachineProduct(int id, Product product, float price, ArrayList<MachineIngredient> recipe) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.recipe = recipe;
    }

    public MachineProduct() {
    }

    public MachineProduct(Product product, float price, ArrayList<MachineIngredient> recipe) {
        this.product = product;
        this.price = price;
        this.recipe = recipe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<MachineIngredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(ArrayList<MachineIngredient> recipe) {
        this.recipe = recipe;
    }
}
