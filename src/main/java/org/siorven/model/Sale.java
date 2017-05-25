package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import java.sql.Timestamp;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private int id;


    @Column(name = "saleDate")
    @Future(groups = {PersistenceGroup.class}, message = "{formatError.DateFormat.NotPast}")
    private Timestamp saleDate;

    @Column(name = "product_quantity")
    @Min(value = 1, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int quantity;

    @ManyToOne
    private MachineProduct product;

    public Sale(Timestamp saleDate, MachineProduct product, int quantity) {
        this.saleDate = saleDate;
        this.product = product;
        this.quantity = quantity;
    }

    public Sale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Timestamp saleDate) {
        this.saleDate = saleDate;
    }

    public MachineProduct getProduct() {
        return product;
    }

    public void setProduct(MachineProduct product) {
        this.product = product;
    }
}
