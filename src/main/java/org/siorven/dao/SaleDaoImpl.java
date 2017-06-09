package org.siorven.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.siorven.model.Machine;
import org.siorven.model.Sale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Gorospe on 25/05/2017.
 */
@Transactional
@Repository
public class SaleDaoImpl implements SaleDao {

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
    public void save(Sale sale) {
        getSession().save(sale);
    }

    @Override
    public void edit(Sale sale) {
        getSession().update(sale);
    }

    @Override
    public void editOrSave(Sale sale) {
        getSession().saveOrUpdate(sale);
    }

    @Override
    public void delete(int id) {
        getSession().delete(findById(id));
    }

    @Override
    public Sale findById(int id) {
        Criteria crit = getSession().createCriteria(Sale.class).add(Restrictions.eq("id", id));
        return (Sale) crit.uniqueResult();
    }

    @Override
    public List<Sale> getAllSales() {
        return getSession().createCriteria(Sale.class).list();
    }

    @Override
    public List<Sale> getSalesFromMachineBetweenDates(Timestamp fromDate, Timestamp toDate, Machine machine) {
        Criteria c = getSession().createCriteria(Sale.class, "sale");
        c.createAlias("sale.product", "prod"); // inner join by default
        c.createAlias("prod.machine", "machine");
        c.add(Restrictions.lt("sale.saleDate", toDate));
        c.add(Restrictions.ge("sale.saleDate", fromDate));
        c.add(Restrictions.eq("machine.id", machine.getId()));

        return c.list();
    }
}
