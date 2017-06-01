package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.Sale;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
public interface SaleDao {
    /**
     * Persists a Sale
     *
     * @param sale The {@link Sale} to be persisted
     */
    void save(@Validated(PersistenceGroup.class) Sale sale);

    /**
     * Updates a persisted sale
     *
     * @param sale The Sale to be persisted
     */
    void edit(@Validated(PersistenceGroup.class) Sale sale);

    /**
     * Updates or persists a Sale
     *
     * @param sale The sale to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) Sale sale);

    /**
     * Deletes a persisted sale
     *
     * @param id The id of the sale to be deleted
     */
    void delete(int id);

    /**
     * Finds a persisted {@link Sale} by its id
     *
     * @param id The id of the sale to be searched
     * @return The sale or null if it wasn't found
     */
    Sale findById(int id);

    /**
     * Returns all the persisted sale
     *
     * @return A {@link List} conta1ining all the sale
     */
    List getAllSales();

    /**
     * Return a list of the sales made on a given date
     *
     * @param date
     * @return
     */
    List getSalesFromMachineBetweenDates(Timestamp fromDate, Timestamp toDate, Machine machine);

}
