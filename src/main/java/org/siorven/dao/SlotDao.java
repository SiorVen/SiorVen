package org.siorven.dao;

import org.siorven.model.Slot;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 24/05/2017.
 */
public interface SlotDao {

    /**
     * Persists a slot
     *
     * @param s The slot to be persisted
     */
    void saveSlot(@Validated(PersistenceGroup.class) Slot s);

    /**
     * Updates a persisted slot
     *
     * @param s The slot to be persisted
     */
    void editSlot(@Validated(PersistenceGroup.class) Slot s);

    /**
     * Updates or persists a slot
     *
     * @param s The slot to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) Slot s);

    /**
     * Deletes a persisted slot
     *
     * @param id The id of the slot to be deleted
     */
    void deleteSlot(int id);

    /**
     * Finds a persisted slot by its id
     *
     * @param id The id of the slot to be searched
     * @return The slot or null if it wasn't found
     */
    Slot findById(int id);

    /**
     * Finds a persisted slot by its name
     *
     * @param name The name of the slot to be searched
     * @return The slot or null if it wasn't found
     */
    Slot findByName(String name);

    /**
     * Returns all the persisted slots
     *
     * @return A {@link List} containing all the slots
     */
    List getAllSlots();


}
