package org.siorven.model;


import org.siorven.model.interfaces.IBoundleRepresentable;
import org.springframework.context.MessageSource;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Locale;

/**
 * Represents a suggestion
 */
@Entity
@Table(name = "suggestion")
public abstract class Suggestion implements IBoundleRepresentable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suggestion_id")
    private int id;

    private double weight;

    private Timestamp generateDate;

    @ManyToOne
    private Machine machine;


    public Suggestion() {
        //empty constructor
    }

    public Suggestion(Timestamp generateDate, Machine machine, double weight) {
        this.generateDate = generateDate;
        this.machine = machine;
        this.weight = weight;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public abstract String printReason(MessageSource messageSource, Locale locale);

    public abstract String printSuggestion(MessageSource messageSource, Locale locale);
}
