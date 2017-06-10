package org.siorven.services;

import org.siorven.dao.SuggestionDao;
import org.siorven.model.Machine;
import org.siorven.model.Suggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Suggestion data access logic
 */
@Service
public class SuggestionService {

    @Autowired
    private SuggestionDao suggestionDao;

    @Autowired
    MachineService machineService;

    /**
     * Save suggestion
     *
     * @param suggestion The suggestion
     */
    public void save(Suggestion suggestion) {
        suggestionDao.saveSuggestion(suggestion);
    }

    /**
     * Edit suggestion
     *
     * @param suggestion The suggestion
     */
    public void edit(Suggestion suggestion) {
        suggestionDao.editSuggestion(suggestion);
    }

    /**
     * Save a suggestion if it is new or update it if it exists
     *
     * @param suggestion The suggestion
     */
    public void saveOrUpdate(Suggestion suggestion) {
        suggestionDao.editOrSaveSuggestion(suggestion);
    }

    /**
     * Delete suggestion
     *
     * @param suggestion The suggestion
     */
    public void delete(Suggestion suggestion) {
        suggestionDao.deleteSuggestion(suggestion.getId());
    }

    /**
     * Get suggestion that has a given id
     *
     * @param id The id of the suggestion
     * @return null if the suggestion wasn't found
     */
    public Suggestion findById(int id) {
        return suggestionDao.findById(id);
    }

    /**
     * Finds all the suggestions
     *
     * @return The list containing all the suggestions
     */
    public List<Suggestion> findAll() {
        return suggestionDao.findAll();
    }

    /**
     * Find the suggestions of a machine
     *
     * @param id The id of the machine
     * @return The list of suggestions
     */
    public List<Suggestion> findByMachine(int id) {
        Machine m = machineService.findById(id);
        return suggestionDao.findByMachine(m);
    }

    /**
     * Save all the suggestions of a list
     *
     * @param suggestions
     */
    public void saveSuggestionList(List<Suggestion> suggestions) {
        for (Suggestion sg : suggestions) {
            suggestionDao.saveSuggestion(sg);
        }
    }
}
