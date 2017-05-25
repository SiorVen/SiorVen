package org.siorven.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="Distribution")
public class MatrixDistribution extends Distribution{

    public static final String ROW = "distribution.matrix.row";
    public static final String COLUMN = "distribution.matrix.column";

    @Column(name="rows")
    private int rows;

    @Column(name="columns")
    private int columns;

    public MatrixDistribution(String Id, String description, int rows, int columns) {
        super(Id, description);
        this.rows = rows;
        this.columns = columns;

        List<Slot> slots = new ArrayList<>();

        for (int i = 0; i < (rows * columns); i++) {
            slots.add(new Slot());
        }

        this.setSlots(slots);
    }

    public MatrixDistribution() {
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public Slot findSlot(HashMap<String, Object> positionParam) {
        int row = (int) positionParam.get(ROW);
        int col = (int) positionParam.get(COLUMN);

        return getSlots().get(row * columns + col);
    }

    @Override
    public Map<String, Class> getPositionParams() {
        HashMap<String, Class> map =  new HashMap<>();
        map.put(COLUMN, Integer.class);
        map.put(ROW, Integer.class);
        return map;
    }
}
