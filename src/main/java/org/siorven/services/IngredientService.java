package org.siorven.services;

import org.siorven.dao.IngredientDao;
import org.siorven.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joseb on 24/05/2017.
 */
@Service
public class IngredientService {

    @Autowired
    private IngredientDao ingredientDao;

    /**
     * Save ingredient into database
     *
     * @param ingredient
     */
    public void save(Ingredient ingredient) {
        ingredientDao.saveIngredient(ingredient);
    }

    /**
     * Update ingredient
     *
     * @param ingredient
     */
    public void edit(Ingredient ingredient) {
        ingredientDao.editIngredient(ingredient);
    }

    /**
     * Save an ingredient if it is new, or update it if it exists
     *
     * @param ingredient
     */
    public void saveOrUpdate(Ingredient ingredient) {
        ingredientDao.editOrSaveIngredient(ingredient);
    }

    /**
     * Delete ingredient
     *
     * @param ingredient
     */
    public void delete(Ingredient ingredient) {
        ingredientDao.deleteIngredient(ingredient.getId());
    }

    /**
     * Get ingredient that has a given id
     *
     * @param id
     * @return null if the ingredient wasn't found
     */
    public Ingredient findById(int id) {
        return ingredientDao.findById(id);
    }

    public List findAll() {
        return ingredientDao.getAllIngredients();
    }
}
