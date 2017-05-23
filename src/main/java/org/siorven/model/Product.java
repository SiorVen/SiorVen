package org.siorven.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 19/05/2017.
 */
@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue
    @Column(name="product_id")
    private int id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> recipe;

    public Product(int id, String name, List<Ingredient> recipe) {
        this.id = id;
        this.name = name;
        this.recipe = recipe;
    }

    public Product(String name, List<Ingredient> recipe) {
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

    public List<Ingredient> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<Ingredient> recipe) {
        this.recipe = recipe;
    }
}
