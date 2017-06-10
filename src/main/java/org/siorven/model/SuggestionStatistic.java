package org.siorven.model;

import org.springframework.context.MessageSource;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Locale;

/**
 * Represents a suggestion whose reason is a statistic (min max) one
 */
@Entity
@Table(name = "suggestion")
public class SuggestionStatistic extends Suggestion {

    public static final boolean MAX = true;
    public static final boolean MIN = false;

    @ManyToOne
    private Product product;

    private boolean isMax;

    public SuggestionStatistic() {
        //empty constructor
    }

    public SuggestionStatistic(Timestamp generateDate, Machine machine, Product product, boolean maxMin, double weight) {
        super(generateDate, machine, weight);
        this.product = product;
        this.isMax = maxMin;
    }


    @Override
    public String printReason(MessageSource messageSource, Locale locale) {
        if (isMax) {
            return messageSource.getMessage("suggestion.soldALot", new String[]{product.getName()}, locale);
        } else {
            return messageSource.getMessage("suggestion.notSoldALot", new String[]{product.getName()}, locale);
        }
    }

    @Override
    public String printSuggestion(MessageSource messageSource, Locale locale) {
        if (isMax) {
            return messageSource.getMessage("suggestion.addX", new String[]{product.getName()}, locale);
        } else {
            return messageSource.getMessage("suggestion.removeX", new String[]{product.getName()}, locale);
        }
    }

    @Override
    public String geyClassKey() {
        return "suggestion.statistic";
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public boolean isMax() {
        return isMax;
    }

    public void setMax(boolean max) {
        this.isMax = max;
    }
}
