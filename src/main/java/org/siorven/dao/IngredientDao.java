package org.siorven.dao;

import org.siorven.model.Ingredient;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by joseb on 24/05/2017.
 */
public interface IngredientDao {

    void saveIngredient(@Validated(PersistenceGroup.class) Ingredient i);

    void editIngredient(@Validated(PersistenceGroup.class) Ingredient i);

    void editOrSaveIngredient(@Validated(PersistenceGroup.class) Ingredient i);

    void deleteIngredient(int id);

    Ingredient findById(int id);

    List getAllIngredients();
}
