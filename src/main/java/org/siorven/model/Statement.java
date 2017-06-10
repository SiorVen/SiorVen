package org.siorven.model;

import javax.persistence.*;

/**
 * Represents a statement (yes no) product
 */
@Entity
@Table(name = "statement")
public class Statement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "statement_id")
    private int id;

    @ManyToOne
    private Product product;

    private boolean statementResult;

    public Statement(Product product, boolean statementResult) {
        this.product = product;
        this.statementResult = statementResult;
    }

    public Statement() {
        //empty constructor
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
