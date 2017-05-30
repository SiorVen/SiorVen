package org.siorven.model;

import javax.persistence.*;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Entity
@Table(name = "statement")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statement_id")
    private int id;

    private double weight;

    @ManyToOne
    private Product product;

    private boolean statementResult;

    public Statement(Product product, boolean statementResult, double weight) {
        this.product = product;
        this.statementResult = statementResult;
        this.weight = weight;
    }

    public Statement() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isStatementResult() {
        return statementResult;
    }

    public void setStatementResult(boolean statementResult) {
        this.statementResult = statementResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
