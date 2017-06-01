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
     * @param messageSource
     * @param resolver
     * @param request
     * @return
     */
    @Override
    public String toString(MessageSource messageSource, LocaleResolver resolver, HttpServletRequest request) {
        StringBuilder stb = new StringBuilder(" ");
        for (Statement s : premiseList) {
            stb.append(s.getProduct().getName() + "{" + s.isStatementResult() + "}; ");
        }
        stb.append(" --> ");

        for (Statement s : consequenceList) {
            stb.append(s.getProduct().getName() + "{" + s.isStatementResult() + "};");
        }
        return stb.toString();
    }

    @Override
    public String getFinalConsequence() {
        StringBuilder stb = new StringBuilder("");
        for (Statement s : consequenceList) {
            stb.append(booleanToString(s.isStatementResult()) + " " + s.getProduct().getName() + "\n");
        }
        return stb.toString();
    }

    private String booleanToString(boolean state) {
        if (state) {
            return "Introducir";
        } else {
            return "Quitar";
        }
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
