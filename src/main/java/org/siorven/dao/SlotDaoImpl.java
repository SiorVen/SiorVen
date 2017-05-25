package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Slot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation of the {@link SlotDao} interface with hibernate persistence onto a jdbc database
 */
@Transactional
@Repository
public class SlotDaoImpl implements SlotDao {

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
    public void saveSlot(Slot s) {
        getSession().save(s);
    }

    @Override
    public void editSlot(Slot s) {
        getSession().update(s);
    }

    @Override
    public void editOrSave(Slot s) {
        getSession().saveOrUpdate(s);
    }

    @Override
    public void deleteSlot(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Slot findById(int id) {
        Criteria crit = getSession().createCriteria(Slot.class).add(Restrictions.eq("id", id));
        return (Slot) crit.uniqueResult();
    }

    @Override
    public Slot findByName(String name) {
        Criteria crit = getSession().createCriteria(Slot.class).add(Restrictions.eq("name", name));
        return (Slot) crit.uniqueResult();
    }

    @Override
    public List getAllSlots() {
        return getSession().createCriteria(Slot.class).list();
    }
}
