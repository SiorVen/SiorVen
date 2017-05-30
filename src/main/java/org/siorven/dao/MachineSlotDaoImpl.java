package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.MachineSlot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 29/05/2017.
 */
@Transactional
@Repository
public class MachineSlotDaoImpl implements MachineSlotDao{

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
    public void saveMachineSlot(MachineSlot machineSlot) {
        getSession().save(machineSlot);
    }

    @Override
    public void editMachineSlot(MachineSlot machineSlot) {
        getSession().update(machineSlot);
    }

    @Override
    public void editOrSaveMachineSlot(MachineSlot machineSlot) {
        getSession().saveOrUpdate(machineSlot);
    }

    @Override
    public void deleteMachineSlot(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public MachineSlot findById(int id) {
        Criteria crit = getSession().createCriteria(MachineSlot.class).add(Restrictions.eq("id", id));
        return (MachineSlot) crit.uniqueResult();
    }

    @Override
    public List findAll() {
        return getSession().createCriteria(MachineSlot.class).list();
    }
}
