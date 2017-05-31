package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Created by Andoni on 21/05/2017.
 */
@Entity
@Table(name = "ingredient")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private int id;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int quantity;

    @OneToOne
    private Resource resource;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    public Ingredient(Resource resource, int quantity) {
        this.quantity = quantity;
        this.resource = resource;
    }

    public Ingredient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
