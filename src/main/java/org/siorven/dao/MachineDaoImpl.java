package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Machine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by joseb on 24/05/2017.
 */
@Transactional
@Repository
public class MachineDaoImpl implements MachineDao {

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
    public void saveMachine(Machine machine) {
        getSession().save(machine);
    }

    @Override
    public void editMachine(Machine machine) {
        getSession().update(machine);
    }

    @Override
    public void editOrSaveMachine(Machine machine) {
        getSession().saveOrUpdate(machine);
    }

    @Override
    public void deleteMachine(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Machine findById(int id) {
        Criteria crit = getSession().createCriteria(Machine.class).add(Restrictions.eq("id", id));
        return (Machine) crit.uniqueResult();
    }

    @Override
    public List<Machine> findAll() {
        return getSession().createCriteria(Machine.class).list();
    }
}
