package org.siorven.model;

import javax.persistence.*;

/**
 * Created by Gorospe on 31/05/2017.
 */
@Entity
@Table(name = "recollector")
public class Recollector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recollector_id")
    private int id;

    @Column(unique = true)
    private String alias;

    @OneToOne
    private Machine machine;

    private boolean connection;

    public Recollector(String alias, boolean connection) {
        this.connection = connection;
        this.alias = alias;
    }

    public Recollector (){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public boolean isConnection() {
        return connection;
    }

    public void setConnection(boolean connection) {
        this.connection = connection;
    }
}
