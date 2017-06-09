package org.siorven.services;

import org.siorven.dao.RecollectorDao;
import org.siorven.model.Recollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Recollector data access logic
 */
@Service
public class RecollectorService {

    @Autowired
    private RecollectorDao recollectorDao;

    /**
     * Save recollector
     *
     * @param recollector The recollector
     */
    public void save(Recollector recollector) {
        recollectorDao.saveRecollector(recollector);
    }

    /**
     * Edit recollector
     *
     * @param recollector The recollector
     */
    public void edit(Recollector recollector) {
        recollectorDao.editRecollector(recollector);
    }

    /**
     * Save a recollector if it is new or update it if it exists
     *
     * @param recollector The recollector
     */
    public void editOrSave(Recollector recollector) {
        recollectorDao.editOrSaveRecollector(recollector);
    }

    /**
     * Delete recollector
     *
     * @param recollector The recollector
     */
    public void delete(Recollector recollector) {
        recollectorDao.deleteRecollector(recollector.getAlias());
    }

    /**
     * Get recollector that has a given id
     *
     * @param alias The alias of the recollector
     * @return null if the recollector wasn't found
     */
    public Recollector findByAlias(String alias) {
        return recollectorDao.findByAlias(alias);
    }

    /**
     * Finds all the recollectors
     *
     * @return The list containing all the recollectors
     */
    public List<Recollector> findAll() {
        return recollectorDao.findAll();
    }
}
