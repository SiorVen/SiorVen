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
 * Represents a distribution based on a matrix where each cell is a slot
 */
@Entity
@Table(name = "Distribution")
public class MatrixDistribution extends Distribution {

    private static final String ROW = "distribution.matrix.row";
    private static final String COLUMN = "distribution.matrix.column";

    @Column(name = "rows")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int rows;

    @Column(name = "columns")
    @Min(value = 0, groups = {PersistenceGroup.class, SpringFormGroup.class, SpringFormEditGroup.class}, message = "{formatError.negativeNumber}")
    private int columns;

    public MatrixDistribution(String description, int rows, int columns) {
        super(description);
        this.rows = rows;
        this.columns = columns;

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
    public Slot findSlot(Map<String, Object> positionParam) {
        int row = (int) positionParam.get(ROW);
        int col = (int) positionParam.get(COLUMN);

        return getSlots().get(row * columns + col);
    }

    @Override
    public Map<String, Class> getPositionParams() {
        Map<String, Class> map = new HashMap<>();
        map.put(COLUMN, Integer.class);
        map.put(ROW, Integer.class);
        return map;
    }
}
