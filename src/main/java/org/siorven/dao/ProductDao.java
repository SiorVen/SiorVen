package org.siorven.dao;

import org.siorven.model.Product;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 24/05/2017.
 */
public interface ProductDao {

    /**
     * Persists a Product
     *
     * @param p The Product to be persisted
     */
    void save(@Validated(PersistenceGroup.class) Product p);

    /**
     * Updates a persisted Product
     *
     * @param p The Product to be persisted
     */
    void edit(@Validated(PersistenceGroup.class) Product p);

    /**
     * Updates or persists a Product
     *
     * @param p The Product to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) Product p);

    /**
     * Deletes a persisted Product
     *
     * @param id The id of the Product to be deleted
     */
    void delete(int id);

    /**
     * Finds a persisted Product by its id
     *
     * @param id The id of the Product to be searched
     * @return The Product or null if it wasn't found
     */
    Product findById(int id);

    /**
     * Finds a persisted Product by its name
     *
     * @param name The name of the Product to be searched
     * @return The Product or null if it wasn't found
     */
    Product findByName(String name);

    /**
     * Returns all the persisted Products
     *
     * @return A {@link List} conta1ining all the Products
     */
    List getAllProducts();
}
