package org.siorven.dao;

import org.siorven.model.Ingredient;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Data access interface for the ingredients Exceptions and null
 * return values are expected if they fail.
 *
 * @see Ingredient
 */
public interface IngredientDao {
    /**
     * Saves an ingredient into the persistence provider
     *
     * @param i The ingredient to be saved
     */
    void saveIngredient(@Validated(PersistenceGroup.class) Ingredient i);

    /**
     * Edits an ingredient present on the persistence provider
     *
     * @param i The ingredient with the modified data
     */
    void editIngredient(@Validated(PersistenceGroup.class) Ingredient i);

    /**
     * Edits an ingredient if its present on the persistence provider and inserts it if not
     *
     * @param i The ingredient
     */
    void editOrSaveIngredient(@Validated(PersistenceGroup.class) Ingredient i);

    /**
     * Deletes the ingredient with the given ID
     *
     * @param id The id of the ingredient to be deleted
     */
    void deleteIngredient(int id);

    /**
     * Finds the persisted ingredient with the given id
     *
     * @param id The id of the ingredient
     * @return The ingredient
     */
    Ingredient findById(int id);

    /**
     * Gets all the persisted ingredients
     *
     * @return The list of ingredients
     */
    List<Ingredient> getAllIngredients();
}
