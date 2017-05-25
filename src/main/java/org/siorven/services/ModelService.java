package org.siorven.services;

import org.siorven.dao.ModelDao;
import org.siorven.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Service
public class ModelService {

    @Autowired
    private ModelDao modelDao;

    public void save(Model model) {
        modelDao.saveModel(model);
    }

    public void edit(Model model) {
        modelDao.editModel(model);
    }

    public void savaOrUpdate(Model model) {
        modelDao.editOrSaveModel(model);
    }

    public void delete(Model model) {
        modelDao.deleteModel(model.getId());
    }

    public Model findById(int id) {
        return modelDao.findById(id);
    }

    public List findAll() {
        return modelDao.findAll();
    }
}
