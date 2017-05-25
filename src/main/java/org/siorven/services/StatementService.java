package org.siorven.services;

import org.siorven.dao.StatementDao;
import org.siorven.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Service
public class StatementService {

    @Autowired
    private StatementDao statementDao;

    public void save(Statement statement) {
        statementDao.saveStatement(statement);
    }

    public void edit(Statement statement) {
        statementDao.editStatement(statement);
    }

    public void saveOrUpdate(Statement statement) {
        statementDao.editOrSaveStatement(statement);
    }

    public void delete(Statement statement) {
        statementDao.deleteStatement(statement.getId());
    }

    public Statement findById(int id) {
        return statementDao.findById(id);
    }

    public List findAll() {
        return statementDao.findAll();
    }


}
