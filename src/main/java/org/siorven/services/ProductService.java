package org.siorven.services;

/**
 * Created by Andoni on 24/05/2017.
 */

import org.siorven.dao.ProductDao;
import org.siorven.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class ProductService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private ProductDao productDao;


    /**
     * Saves a product to the database.
     *
     * @param product The product to be saved
     */
    public void save(Product product) {
        productDao.save(product);
    }

    /**
     * Saves or updates a product on the database
     *
     * @param product product to be saved or updated
     */
    public void saveOrUpdate(Product product) {
        productDao.editOrSave(product);
    }

    /**
     * Updates a product on the database
     *
     * @param product product to be updated
     */
    public void edit(Product product) {
        productDao.edit(product);
    }

    /**
     * Deletes a product from the database
     *
     * @param id The id of the product to be deleted from the database
     */
    public void delete(int id) {
        productDao.delete(id);
    }

    /**
     * Searches for the product with the given id
     *
     * @param id The id of the requested product
     * @return The product or null if it wasn't found
     */
    public Product findById(int id) {
        return productDao.findById(id);
    }

    /**
     * Searches for the product with the given name
     *
     * @param name The name of the requested product
     * @return The product or null if it wasn't found
     */
    public Product findByName(String name) {
        return productDao.findByName(name);
    }

    /**
     * Returns all the product on the database
     *
     * @return The list of product
     */
    public List findAll() {
        return productDao.getAllProducts();
    }

}
