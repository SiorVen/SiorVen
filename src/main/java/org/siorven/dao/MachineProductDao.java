package org.siorven.dao;

import org.siorven.model.Machine;
import org.siorven.model.MachineProduct;
import org.siorven.model.Product;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access layer for the products in machine
 *
 * @see MachineProduct
 */
public interface MachineProductDao {

    /**
     * Stores a machine product on the persistence provider
     *
     * @param machineProduct The machine product
     */
    void saveMachineProduct(@Validated(PersistenceGroup.class) MachineProduct machineProduct);

    /**
     * Edits a machine product on the persistence provider
     *
     * @param machineProduct The machine product
     */
    void editMachineProduct(@Validated(PersistenceGroup.class) MachineProduct machineProduct);

    /**
     * Edits or saves the given machine product on the persistence provider
     *
     * @param machineProduct The machine product
     */
    void editOrSaveMachineProduct(@Validated(PersistenceGroup.class) MachineProduct machineProduct);

    /**
     * Deletes a machine product from the persistence provider
     *
     * @param id The id of the machine product
     */
    void deleteMachineProduct(int id);

    /**
     * Finds the persisted machine Product with the given ID
     *
     * @param id The id of the persisted machine
     * @return The machine product or null if it wasn't found
     */
    MachineProduct findById(int id);

    /**
     * Find all the persisted machine products
     *
     * @return the machine product list
     */
    List findAll();

    /**
     * Finds the persisted machine product with the given product
     *
     * @param p The product
     * @return The list of machine products
     */
    List<MachineProduct> findByProduct(Product p);

    /**
     * Finds the machine products within the given machine
     *
     * @param machine The machine
     * @return The list of machine products
     */
    List<MachineProduct> findByMachine(Machine machine);
}
