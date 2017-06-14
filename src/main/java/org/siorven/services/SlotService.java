package org.siorven.services;

import org.siorven.dao.SlotDao;
import org.siorven.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
     * Data acces object for the machineSlot table on the database
     */
    @Autowired
    private MachineSlotService machineslotService;

    /**
     * Data acces object for the machine table on the database
     */
    @Autowired
    private MachineService machineService;

    /**
     * Data acces object for the machine table on the database
     */
    @Autowired
    private MachineModelService machineModelService;

    /**
     * Data acces object for the distribution table on the database
     */
    @Autowired
    private DistributionService distributionService;

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

    /**
     * Returns the free the slots on the database
     *
     * @return The list of slots
     */
    public List findFree(int id) {
        Machine machine = machineService.findById(id);

        MachineModel model = machineModelService.findById(machine.getMachineModel().getId());
        List<Distribution> distributions = distributionService.findByModel(model);
        List<Slot> slotsByDistribution;
        List<Slot> slots = new ArrayList<>();
        for(int j = 0; j < distributions.size(); j++){
            slotsByDistribution = distributions.get(j).getSlots();
            slots.addAll(slotsByDistribution);
        }
        List<MachineSlot> machineSlots = machineslotService.findByMachineId(id);
        for (int i = 0; i < slots.size(); i++) {
            for (int j = 0; j < machineSlots.size(); j++) {
                if (machineSlots.get(j).getSlot().getId() == slots.get(i).getId()) {
                    slots.remove(i);
                }
            }

        }
        return slots;
    }


}
