package org.siorven.model;

import org.hibernate.annotations.IndexColumn;
import org.springframework.context.MessageSource;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Represents a suggestion whose reason is an association rule
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

    @Override
    public String printReason(MessageSource messageSource, Locale locale) {
        StringBuilder stb = new StringBuilder("");
        String and = messageSource.getMessage("suggestion.and", null, locale);
        String not = messageSource.getMessage("suggestion.not", null, locale);
        for (int i = 0; i < premiseList.size(); i++) {
            Statement s = premiseList.get(i);
            if (i != 0)
                if (i != premiseList.size() - 1)
                    stb.append(", ");
                else
                    stb.append(" ").append(and).append(" ");
            if (!s.isStatementResult())
                stb.append(not).append(" ");
            stb.append(s.getProduct().getName()).append(i);
        }
        return messageSource.getMessage("suggestion.isSold", new String[]{stb.toString()}, locale);
    }

    @Override
    public String printSuggestion(MessageSource messageSource, Locale locale) {
        StringBuilder stb = new StringBuilder("");
        String add = messageSource.getMessage("suggestion.add", null, locale);
        String and = messageSource.getMessage("suggestion.and", null, locale);
        String remove = messageSource.getMessage("suggestion.remove", null, locale);
        for (int i = 0; i < consequenceList.size(); i++) {
            Statement s = consequenceList.get(i);
            if (i != 0)
                if (i != consequenceList.size() - 1)
                    stb.append(", ");
                else
                    stb.append(" ").append(and).append(" ");
            stb.append(s.isStatementResult() ? add : remove).append(" ");
            stb.append(s.getProduct().getName()).append(i);
        }
        return stb.toString();
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
