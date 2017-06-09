package org.siorven.services;

import org.siorven.dao.StatementDao;
import org.siorven.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Statement data access logic
 */
@Service
public class StatementService {

    @Autowired
    private StatementDao statementDao;

    /**
     * Save statement
     *
     * @param statement The statement
     */
    public void save(Statement statement) {
        statementDao.saveStatement(statement);
    }

    /**
     * Edit statement
     *
     * @param statement The statement
     */
    public void edit(Statement statement) {
        statementDao.editStatement(statement);
    }

    /**
     * Save a statement if it is new or update it if it exists
     *
     * @param statement The statement
     */
    public void saveOrUpdate(Statement statement) {
        statementDao.editOrSaveStatement(statement);
    }

    /**
     * Delete statement
     *
     * @param statement The statement
     */
    public void delete(Statement statement) {
        statementDao.deleteStatement(statement.getId());
    }

    /**
     * Get statement that has a given id
     *
     * @param id The id of the statement
     * @return null if the statement wasn't found
     */
    public Statement findById(int id) {
        return statementDao.findById(id);
    }

    /**
     * Finds all the statements
     *
     * @return The list containing all the statements
     */
    public List findAll() {
        return statementDao.findAll();
    }


}
