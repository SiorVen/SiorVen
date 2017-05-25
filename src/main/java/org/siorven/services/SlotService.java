package org.siorven.services;

import org.siorven.dao.SlotDao;
import org.siorven.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class SlotService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private SlotDao slotDao;

    /**
     * Saves a slot to the database.
     *
     * @param s The slot to be saved
     */
    public void save(Slot s) {
        slotDao.saveSlot(s);
    }

    /**
     * Saves or updates a slot on the database
     *
     * @param s Slot to be saved or updated
     */
    public void saveOrUpdate(Slot s) {
        slotDao.editOrSave(s);
    }

    /**
     * Updates a slot on the database
     *
     * @param s Slot to be updated
     */
    public void edit(Slot s) {
        slotDao.editSlot(s);
    }

    /**
     * Deletes a slot from the database
     *
     * @param id The id of the slot to be deleted from the database
     */
    public void delete(int id) {
        slotDao.deleteSlot(id);
    }

    /**
     * Searches for the slot with the given id
     *
     * @param id The id of the requested slot
     * @return The slot or null if it wasn't found
     */
    public Slot findById(int id) {
        return slotDao.findById(id);
    }

    /**
     * Searches for the slot with the given name
     *
     * @param name The name of the requested slot
     * @return The slot or null if it wasn't found
     */
    public Slot findByName(String name) {
        return slotDao.findByName(name);
    }

    /**
     * Returns all the slots on the database
     *
     * @return The list of slots
     */
    public List findAll() {
        return slotDao.getAllSlots();
    }

}
