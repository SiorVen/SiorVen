package org.siorven.dao;

import org.siorven.model.ProductType;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 24/05/2017.
 */
public interface ProductTypeDao {
    /**
     * Persists a Product Type
     *
     * @param p The Product Type to be persisted
     */
    void saveProductType(@Validated(PersistenceGroup.class) ProductType p);

    /**
     * Updates a persisted Product Type
     *
     * @param p The Product Type to be persisted
     */
    void edit(@Validated(PersistenceGroup.class) ProductType p);

    /**
     * Updates or persists a Product Type
     *
     * @param p The Product Type to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) ProductType p);

    /**
     * Deletes a persisted Product Type
     *
     * @param id The id of the Product Type to be deleted
     */
    void delete(String id);

    /**
     * Finds a persisted Product Type by its id
     *
     * @param id The id of the Product Type to be searched
     * @return The Product Type or null if it wasn't found
     */
    ProductType findById(String id);

    /**
     * Finds a persisted Product Type by its name
     *
     * @param type The name of the Product Type to be searched
     * @return The Product Type or null if it wasn't found
     */
    ProductType findByType(String type);

    /**
     * Returns all the persisted Product Types
     *
     * @return A {@link List} containing all the Product Types
     */
    List getAllProductTypes();
}
