package org.siorven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="Distribution")
public class MatrixDistribution extends Distribution{

    @Column(name="lines")
    private int lines;

    @Column(name="columns")
    private int columns;

    public MatrixDistribution(String Id, String description, int lines, int columns) {
        super(Id, description);
        this.lines = lines;
        this.columns = columns;
    }

    public MatrixDistribution() {
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
