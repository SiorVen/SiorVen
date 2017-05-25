package org.siorven.model;

import org.siorven.model.validacion.PersistenceGroup;
import org.siorven.model.validacion.SpringFormEditGroup;
import org.siorven.model.validacion.SpringFormGroup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andoni on 16/05/2017.
 */
@Entity
@Table(name="Distribution")
public class MatrixDistribution extends Distribution{

    public static final String ROW = "distribution.matrix.row";
    public static final String COLUMN = "distribution.matrix.column";

    @Column(name = "lines")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int lines;

    @Column(name="rows")
    private int rows;

    @Column(name = "columns")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int columns;

    public MatrixDistribution(String description, int lines, int columns) {
        super(description);
        this.lines = lines;
        this.columns = columns;
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
