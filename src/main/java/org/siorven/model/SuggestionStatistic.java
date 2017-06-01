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

    public static boolean MAX = true;
    public static boolean MIN = false;

    @ManyToOne
    private Product product;

    private boolean reason;

    public SuggestionStatistic() {
    }

    public SuggestionStatistic(Timestamp generateDate, Machine machine, Product product, boolean maxMin, double weight) {
        super(generateDate, machine, weight);
        this.product = product;
        this.reason = maxMin;
    }

    /**
     * @param messageSource
     * @param resolver
     * @param request
     * @return
     */
    @Override
    public String toString(MessageSource messageSource, LocaleResolver resolver, HttpServletRequest request) {
        return "Los productos mas y menos vendidos";
    }

    @Override
    public String getFinalConsequence() {
        return "Introducir " + product.getName();
    }

    @Override
    public String geyClassKey() {
        return "suggestion.statistic";
    }
}
