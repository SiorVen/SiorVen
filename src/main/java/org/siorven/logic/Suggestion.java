package org.siorven.logic;


import org.siorven.model.Product;

/**
 * Created by Andoni on 22/05/2017.
 */
public class Suggestion {

    private Product premise;
    private boolean premiseMode;

    private Product consequence;
    private boolean consequenceMode;

    public Suggestion(Product premise, boolean premiseMode, Product consequence, boolean consequenceMode) {
        this.premise = premise;
        this.premiseMode = premiseMode;
        this.consequence = consequence;
        this.consequenceMode = consequenceMode;
    }

    public Product getPremise() {
        return premise;
    }

    public void setPremise(Product premise) {
        this.premise = premise;
    }

    public Product getConsequence() {
        return consequence;
    }

    public void setConsequence(Product consequence) {
        this.consequence = consequence;

    }

    public boolean isPremiseMode() {
        return premiseMode;
    }

    public void setPremiseMode(boolean premiseMode) {
        this.premiseMode = premiseMode;
    }

    public boolean isConsequenceMode() {
        return consequenceMode;
    }

    public void setConsequenceMode(boolean consequenceMode) {
        this.consequenceMode = consequenceMode;
    }

}
