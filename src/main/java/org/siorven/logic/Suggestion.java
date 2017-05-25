package org.siorven.logic;


import org.siorven.model.Machine;
import org.siorven.model.Statement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andoni on 22/05/2017.
 */
public class Suggestion {

    private List<Statement> premiseList;

    private List<Statement> consequenceList;

    private Timestamp generateDate;

    private Machine machine;


    public Suggestion(Timestamp generateDate, Machine machine) {
        this.generateDate = generateDate;
        this.machine = machine;
        premiseList = new ArrayList<>();
        consequenceList = new ArrayList<>();
    }

    public Timestamp getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Timestamp generateDate) {
        this.generateDate = generateDate;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public List<Statement> getPremiseList() {
        return premiseList;
    }

    public List<Statement> getConsequenceList() {
        return consequenceList;
    }

    public void setPremiseList(List<Statement> premiseList) {
        this.premiseList = premiseList;
    }

    public void setConsequenceList(List<Statement> consequenceList) {
        this.consequenceList = consequenceList;
    }
}
