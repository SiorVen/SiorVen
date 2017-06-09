package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.MachineIngredient;
import org.siorven.model.MachineProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Transactional
@Repository
public class MachineIngredientDaoImpl implements MachineIngredientDao {

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
    public void saveMachineIngredient(MachineIngredient machineIngredient) {
        getSession().save(machineIngredient);
    }

    @Override
    public void editMachineIngredient(MachineIngredient machineIngredient) {
        getSession().update(machineIngredient);
    }

    @Override
    public void editOrSaveMachineIngredient(MachineIngredient machineIngredient) {
        getSession().saveOrUpdate(machineIngredient);
    }

    @Override
    public void deleteMachineIngredient(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public MachineIngredient findById(int id) {
        Criteria criteria = getSession().createCriteria(MachineIngredient.class).add(Restrictions.eq("id", id));
        return (MachineIngredient) criteria.uniqueResult();
    }

    @Override
    public List<MachineIngredient> findAll() {
        return getSession().createCriteria(MachineIngredient.class).list();
    }

    @Override
    public List getRecipeFromMachineProduct(MachineProduct machineProduct) {
        Criteria c = getSession().createCriteria(MachineIngredient.class, "machine_ingredient");
        c.createAlias("machine_ingredient.machineProduct", "mp"); // inner join by default
        c.add(Restrictions.eq("mp.id", machineProduct.getId()));

        return c.list();
    }
}
