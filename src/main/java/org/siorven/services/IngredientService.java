package org.siorven.services;

import org.siorven.dao.IngredientDao;
import org.siorven.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Access logic for the ingredients
 */
@Service
public class IngredientService {

    @Autowired
    private IngredientDao ingredientDao;

    /**
     * Save ingredient into database
     *
     * @param ingredient The ingredient
     */
    public void save(Ingredient ingredient) {
        ingredientDao.saveIngredient(ingredient);
    }

    /**
     * Update ingredient
     *
     * @param ingredient The ingredient
     */
    public void edit(Ingredient ingredient) {
        ingredientDao.editIngredient(ingredient);
    }

    /**
     * Save an ingredient if it is new, or update it if it exists
     *
     * @param ingredient The ingredient
     */
    public void saveOrUpdate(Ingredient ingredient) {
        ingredientDao.editOrSaveIngredient(ingredient);
    }

    /**
     * Delete ingredient
     *
     * @param ingredient The ingredient
     */
    public void delete(Ingredient ingredient) {
        ingredientDao.deleteIngredient(ingredient.getId());
    }

    /**
     * Get ingredient that has a given id
     *
     * @param id The ingredient id
     * @return null if the ingredient wasn't found
     */
    public Ingredient findById(int id) {
        return ingredientDao.findById(id);
    }

    /**
     * Gets all the ingredients
     *
     * @return The ingredient list
     */
    public List<Ingredient> findAll() {
        return ingredientDao.getAllIngredients();
    }
}
