package org.siorven.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 19/05/2017.
 */

@Entity
@Table(name="machine_product")
public class MachineProduct {

    @Id
    int id;

    @ManyToOne
    private Product product;

    private float price;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "machine_ingredient", joinColumns = {
            @JoinColumn(name = "machine_ingredient_id", nullable = false, updatable = false) })
    private List<MachineIngredient> recipe;


    public MachineProduct(int id, Product product, float price, List<MachineIngredient> recipe) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.recipe = recipe;
    }

    public MachineProduct() {
    }

    public MachineProduct(Product product, float price, List<MachineIngredient> recipe) {
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

    public List<MachineIngredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<MachineIngredient> recipe) {
        this.recipe = recipe;
    }
}
