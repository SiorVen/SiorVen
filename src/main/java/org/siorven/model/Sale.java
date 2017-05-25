package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.sql.Date;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Entity
@Table(name="sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sale_id")
    private int id;


    @Column(name = "saleDate")
    @Future(groups = {PersistenceGroup.class}, message = "{formatError.DateFormat.NotPast}")
    private Date saleDate;

    @ManyToOne
    private MachineProduct product;

    public Sale(Date saleDate, MachineProduct product) {
        this.saleDate = saleDate;
        this.product = product;
    }

    public Sale() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public MachineProduct getProduct() {
        return product;
    }

    public void setProduct(MachineProduct product) {
        this.product = product;
    }
}
