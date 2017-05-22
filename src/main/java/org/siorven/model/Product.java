package org.siorven.model;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Andoni on 19/05/2017.
 */
public class Product {

    @Id
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ingredient", joinColumns = {
            @JoinColumn(name = "ingredient_id", nullable = false, updatable = false) })
    private ArrayList<Ingredient> recipe;

    public Product(int id, String name, ArrayList<Ingredient> recipe) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
    }

    public Product(String name, ArrayList<Ingredient> recipe) {
        this.name = name;
        this.recipe = recipe;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Ingredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(ArrayList<Ingredient> recipe) {
        this.recipe = recipe;
    }
}
