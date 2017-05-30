package org.siorven.model;

import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Entity
@Table(name = "suggestion")
public class SuggestionStatistic extends Suggestion {


    @ManyToOne
    private Product maxProduct;

    @ManyToOne
    private Product minProduct;

    public SuggestionStatistic() {
    }

    public SuggestionStatistic(Timestamp generateDate, Machine machine, Product max, Product min, double weight) {
        super(generateDate, machine, weight);
        this.maxProduct = max;
        this.minProduct = min;
    }

    /**
     * TODO behar dan moduen ipini, momentuz MIERDA bat
     * @param messageSource
     * @param resolver
     * @param request
     * @return
     */
    @Override
    public String toString(MessageSource messageSource, LocaleResolver resolver, HttpServletRequest request) {
        return "Max: " + maxProduct.getName() + "\nMin: " + minProduct.getName();
    }

    public Product getMaxProduct() {
        return maxProduct;
    }

    public void setMaxProduct(Product maxProduct) {
        this.maxProduct = maxProduct;
    }

    public Product getMinProduct() {
        return minProduct;
    }

    public void setMinProduct(Product minProduct) {
        this.minProduct = minProduct;
    }
}
