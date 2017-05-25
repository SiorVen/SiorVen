package org.siorven.model;

/**
 * Created by Gorospe on 25/05/2017.
 */
public class Statement {

    private Product product;
    private boolean statementResult;

    public Statement(Product product, boolean statementResult) {
        this.product = product;
        this.statementResult = statementResult;
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
}
