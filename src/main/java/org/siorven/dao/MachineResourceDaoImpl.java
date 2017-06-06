package org.siorven.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Machine;
import org.siorven.model.MachineResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the {@link MachineResourceDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class MachineResourceDaoImpl implements MachineResourceDao {

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
    public void save(MachineResource r) {
        getSession().save(r);
    }

    @Override
    public void edit(MachineResource r) {
        getSession().update(r);
    }

    @Override
    public void editOrSave(MachineResource r) {
        getSession().saveOrUpdate(r);
    }

    @Override
    public void delete(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public MachineResource findById(int id) {
        Criteria crit = getSession().createCriteria(MachineResource.class).add(Restrictions.eq("id", id));
        return (MachineResource) crit.uniqueResult();
    }

    @Override
    public List getAllResources() {
        return getSession().createCriteria(MachineResource.class).list();
    }

    @Override
    public List findByMachine(Machine m) {
        Criteria c = getSession().createCriteria(MachineResource.class, "machine_resource");
        c.createAlias("machine_resource.machineSlot", "mslot"); // inner join by default
        c.createAlias("mslot.machine", "machine");
        c.add(Restrictions.eq("machine.id", m.getId())).addOrder(Order.desc("id"));
        return c.list();
    }
}
