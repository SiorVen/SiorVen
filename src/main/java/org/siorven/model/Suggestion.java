package org.siorven.model;


import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

/**
 * Created by Andoni on 22/05/2017.
 */
@Entity
@Table(name = "suggestion")
public abstract class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suggestion_id")
    private int id;

    private double weight;

    private Timestamp generateDate;

    @ManyToOne
    private Machine machine;


    public Suggestion() {
    }

    public Suggestion(Timestamp generateDate, Machine machine, double weight) {
        this.generateDate = generateDate;
        this.machine = machine;
        this.weight = weight;
    }

    public abstract String toString(MessageSource messageSource, LocaleResolver resolver, HttpServletRequest request);


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
}
