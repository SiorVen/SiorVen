package org.siorven.model.validacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by joseb on 23/05/2017.
 */
@Entity
@Table(name="product_type")
public class ProductType {
    @Id
    @GeneratedValue
    private int id;

    private String type;

    public ProductType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
