package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joseb on 24/05/2017.
 */
@Transactional
@Repository
public class IngredientDaoImpl implements IngredientDao {

    /**
     * Session factory for the jdbc connection bean
     */
    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Returns the current session of the {@link #sessionFactory}
     *
     * @return The current {@link Session}
     */
    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void saveIngredient(Ingredient i) {
        getSession().save(i);
    }

    @Override
    public void editIngredient(Ingredient i) {
        getSession().update(i);
    }

    @Override
    public void editOrSaveIngredient(Ingredient i) {
        getSession().saveOrUpdate(i);
    }

    @Override
    public void deleteIngredient(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Ingredient findById(int id) {
        Criteria crit = getSession().createCriteria(Ingredient.class).add(Restrictions.eq("id", id));
        return (Ingredient) crit.uniqueResult();
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return getSession().createCriteria(Ingredient.class).list();
    }
}
