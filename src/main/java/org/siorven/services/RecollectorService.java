package org.siorven.services;

import org.siorven.dao.RecollectorDao;
import org.siorven.model.Recollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 31/05/2017.
 */
@Service
public class RecollectorService {

    @Autowired
    private RecollectorDao recollectorDao;

    public void save(Recollector recollector){
        recollectorDao.saveRecollector(recollector);
    }

    public void edit(Recollector recollector){
        recollectorDao.editRecollector(recollector);
    }

    public void editOrSave(Recollector recollector){
        recollectorDao.editOrSaveRecollector(recollector);
    }

    public void delete(Recollector recollector){
        recollectorDao.deleteRecollector(recollector.getAlias());
    }

    public Recollector findByAlias(String alias){
        return recollectorDao.findByAlias(alias);
    }

    public List<Recollector> findAll(){
        return recollectorDao.findAll();
    }
}
