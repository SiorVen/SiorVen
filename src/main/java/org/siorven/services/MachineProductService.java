package org.siorven.services;

import org.siorven.dao.MachineProductDao;
import org.siorven.model.Machine;
import org.siorven.model.MachineProduct;
import org.siorven.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Access logic provider for Machine Products
 */
@Service
public class MachineProductService {

    @Autowired
    private MachineProductDao machineProductDao;

    /**
     * Save machine product into database
     *
     * @param machineProduct The machine product
     */
    public void save(MachineProduct machineProduct) {
        machineProductDao.saveMachineProduct(machineProduct);
    }

    /**
     * Update machine product
     *
     * @param machineProduct The machine product
     */
    public void edit(MachineProduct machineProduct) {
        machineProductDao.editMachineProduct(machineProduct);
    }

    /**
     * Save a machine product if it is new, or update it if it exists
     *
     * @param machineProduct The machine product
     */
    public void saveOrUpdate(MachineProduct machineProduct) {
        machineProductDao.editOrSaveMachineProduct(machineProduct);
    }

    /**
     * Delete machine product
     *
     * @param machineProduct The machine product
     */
    public void delete(MachineProduct machineProduct) {
        machineProductDao.deleteMachineProduct(machineProduct.getId());
    }

    /**
     * Get machine product that has a given id
     *
     * @param id The id of the machine product
     * @return null if the machine product wasn't found
     */
    public MachineProduct findById(int id) {
        return machineProductDao.findById(id);
    }

    /**
     * Gets all the machine products
     *
     * @return The machine product list
     */
    public List<MachineProduct> findAll() {
        return machineProductDao.findAll();
    }

    /**
     * Gets the machine products of a product
     *
     * @param p The product
     * @return The list of machineProduct
     */
    public List<MachineProduct> findByProduct(Product p) {
        return machineProductDao.findByProduct(p);
    }

    /**
     * Gets the machine products of a machine
     *
     * @param machine The machine
     * @return The list of machineProduct
     */
    public List<MachineProduct> findByMachine(Machine machine) {
        return machineProductDao.findByMachine(machine);
    }
}
