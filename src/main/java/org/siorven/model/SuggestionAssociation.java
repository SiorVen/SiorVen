package org.siorven.model;

import org.hibernate.annotations.IndexColumn;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Entity
@Table(name = "suggestion")
public class SuggestionAssociation extends Suggestion {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "premise_suggestion_id")
    @IndexColumn(base = 1, name = "dnr")
    private List<Statement> premiseList;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "consequence_suggestion_id")
    @IndexColumn(base = 1, name = "pmnr")
    private List<Statement> consequenceList;

    public SuggestionAssociation() {
    }

    public SuggestionAssociation(Timestamp generateDate, Machine machine, double weight) {
        super(generateDate, machine, weight);
        premiseList = new ArrayList<>();
        consequenceList = new ArrayList<>();
    }

    /**
     * TODO mensajean gestioa ondo in. Momentuz tostring simplea debugeetako
     * @param messageSource
     * @param resolver
     * @param request
     * @return
     */
    @Override
    public String toString(MessageSource messageSource, LocaleResolver resolver, HttpServletRequest request) {
        String ret = "if ";
        for (Statement s : premiseList){
            ret = ret + s.getProduct().getName() + "{" + s.isStatementResult() + "}; ";
        }

        ret = ret + " --> ";

        for (Statement s : consequenceList){
            ret = ret + s.getProduct().getName() + "{" + s.isStatementResult() + "};";
        }
        return ret;
    }

    public List<Statement> getPremiseList() {
        return premiseList;
    }

    public void setPremiseList(List<Statement> premiseList) {
        this.premiseList = premiseList;
    }

    public List<Statement> getConsequenceList() {
        return consequenceList;
    }

    public void setConsequenceList(List<Statement> consequenceList) {
        this.consequenceList = consequenceList;
    }

    @Override
    public String geyClassKey() {
        return "suggestion.association";
    }
}
