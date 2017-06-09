package org.siorven.services;

import org.siorven.dao.ProductTypeDao;
import org.siorven.model.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class ProductTypeService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private ProductTypeDao productTypeDao;

    /**
     * Saves a product type to the database.
     *
     * @param p The product type to be saved
     */
    public void save(ProductType p) {
        productTypeDao.saveProductType(p);
    }

    /**
     * Saves or updates a product type on the database
     *
     * @param p product type to be saved or updated
     */
    public void saveOrUpdate(ProductType p) {
        productTypeDao.editOrSave(p);
    }

    /**
     * Updates a product type on the database
     *
     * @param p product type to be updated
     */
    public void edit(ProductType p) {
        productTypeDao.edit(p);
    }

    /**
     * Deletes a product type from the database
     *
     * @param id The id of the product type to be deleted from the database
     */
    public void delete(String id) {
        productTypeDao.delete(id);
    }

    /**
     * Searches for the product type with the given id
     *
     * @param id The id of the requested product type
     * @return The product type or null if it wasn't found
     */
    public ProductType findById(String id) {
        return productTypeDao.findById(id);
    }

    /**
     * Searches for the product type with the given type
     *
     * @param type The type of the requested product type
     * @return The product type or null if it wasn't found
     */
    public ProductType findByType(String type) {
        return productTypeDao.findByType(type);
    }

    /**
     * Returns all the product type on the database
     *
     * @return The list of product type
     */
    public List<ProductType> findAll() {
        return productTypeDao.getAllProductTypes();
    }
}
