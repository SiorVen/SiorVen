package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
public class MachineProductDaoImpl implements MachineProductDao {

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
    public void saveMachineProduct(MachineProduct machineProduct) {
        getSession().save(machineProduct);
    }

    @Override
    public void editMachineProduct(MachineProduct machineProduct) {
        getSession().update(machineProduct);
    }

    @Override
    public void editOrSaveMachineProduct(MachineProduct machineProduct) {
        getSession().saveOrUpdate(machineProduct);
    }

    @Override
    public void deleteMachineProduct(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public MachineProduct findById(int id) {
        Criteria crit = getSession().createCriteria(MachineProduct.class).add(Restrictions.eq("id", id));
        return (MachineProduct) crit.uniqueResult();
    }

    @Override
    public List findAll() {
        return getSession().createCriteria(MachineProduct.class).list();
    }
}
