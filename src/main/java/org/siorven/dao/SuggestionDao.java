package org.siorven.dao;

import org.siorven.model.Suggestion;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
public interface SuggestionDao {

    void saveSuggestion(@Validated(PersistenceGroup.class) Suggestion suggestion);

    void editSuggestion(@Validated(PersistenceGroup.class) Suggestion suggestion);

    void editOrSaveSuggestion(@Validated(PersistenceGroup.class) Suggestion suggestion);

    void deleteSuggestion(int id);

    Suggestion findById(int id);

    List findAll();

}
