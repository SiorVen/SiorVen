package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

/**
 * Created by Andoni on 19/05/2017.
 */

@Entity
@Table(name = "machine_product")
public class MachineProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machine_product_id")
    int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private float price;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "machineProduct")
    private List<MachineIngredient> recipe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "machine_id")
    private Machine machine;


    public MachineProduct(Product product, float price, List<MachineIngredient> recipe, Machine machine) {
        this.product = product;
        this.price = price;
        this.recipe = recipe;
        this.machine = machine;
    }

    public MachineProduct() {
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
