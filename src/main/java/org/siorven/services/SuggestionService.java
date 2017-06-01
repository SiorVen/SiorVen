package org.siorven.services;

import org.siorven.dao.SuggestionDao;
import org.siorven.model.Machine;
import org.siorven.model.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Service
public class SuggestionService {

    @Autowired
    private SuggestionDao suggestionDao;

    @Autowired
    MachineService machineService;

    public void save(Suggestion suggestion) {
        suggestionDao.saveSuggestion(suggestion);
    }

    public void edit(Suggestion suggestion) {
        suggestionDao.editSuggestion(suggestion);
    }

    public void saveOrUpdate(Suggestion suggestion) {
        suggestionDao.editOrSaveSuggestion(suggestion);
    }

    public void delete(Suggestion suggestion) {
        suggestionDao.deleteSuggestion(suggestion.getId());
    }

    public Suggestion findById(int id) {
        return suggestionDao.findById(id);
    }

    public List findAll() {
        return suggestionDao.findAll();
    }

    public List findByMachine(int id) {
        Machine m = machineService.findById(id);
        return suggestionDao.findByMachine(m);
    }

    //TODO: Aqui deberian estar las funciones que arranquen el analisis de los datos por si se quiere lanzar a mano.
}
