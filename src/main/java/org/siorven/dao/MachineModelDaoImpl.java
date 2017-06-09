package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.MachineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Gorospe on 24/05/2017.
 */
@Transactional
@Repository
public class MachineModelDaoImpl implements MachineModelDao {

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
    public void saveModel(MachineModel machineModel) {
        getSession().save(machineModel);
    }

    @Override
    public void editModel(MachineModel machineModel) {
        getSession().update(machineModel);
    }

    @Override
    public void editOrSaveModel(MachineModel machineModel) {
        getSession().saveOrUpdate(machineModel);
    }

    @Override
    public void deleteModel(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public MachineModel findById(int id) {
        Criteria criteria = getSession().createCriteria(MachineModel.class).add(Restrictions.eq("id", id));
        return (MachineModel) criteria.uniqueResult();
    }

    @Override
    public List<MachineModel> findAll() {
        return getSession().createCriteria(MachineModel.class).list();
    }
}
