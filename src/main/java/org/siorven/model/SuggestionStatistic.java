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

    public SuggestionStatistic(Timestamp generateDate, Machine machine) {
        super(generateDate, machine);
    }

    @Override
    public String toString(MessageSource messageSource, LocaleResolver resolver, HttpServletRequest request) {
        return null;
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
