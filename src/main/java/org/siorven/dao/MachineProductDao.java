package org.siorven.dao;

import org.siorven.model.MachineProduct;
import org.siorven.model.Slot;
import org.siorven.model.Product;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
public interface MachineProductDao {

    void saveMachineProduct(@Validated(PersistenceGroup.class) MachineProduct machineProduct);

    void editMachineProduct(@Validated(PersistenceGroup.class) MachineProduct machineProduct);

    void editOrSaveMachineProduct(@Validated(PersistenceGroup.class) MachineProduct machineProduct);

    void deleteMachineProduct(int id);

    MachineProduct findById(int id);

    List findAll();

    MachineProduct getMachineProductFromSlot(Slot slot);

    List<MachineProduct> findByProduct(Product p);
}
