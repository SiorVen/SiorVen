package org.siorven.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andoni on 19/05/2017.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(unique = true)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "product")
    private List<Ingredient> recipe;

    public Product(String name, List<Ingredient> recipe) {
        this.name = name;
        this.recipe = recipe;
        for (Ingredient i : recipe) {
            i.setProduct(this);
        }
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
