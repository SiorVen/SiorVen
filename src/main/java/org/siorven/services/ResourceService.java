package org.siorven.services;

import org.siorven.dao.ResourceDao;
import org.siorven.exceptions.ResourceAlreadyRegistered;
import org.siorven.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to provide access logic to the user data
 */
@Service
public class ResourceService {

    /**
     * Data acces object for the user table on the database
     */
    @Autowired
    private ResourceDao resourceDao;

    /**
     * Saves a resource to the database.
     *
     * @param r The resource to be saved
     */
    public void save(Resource r) throws ResourceAlreadyRegistered {
        Resource oldResource = findByName(r.getName());

        if(oldResource == null) {
            resourceDao.save(r);
        } else {
            throw new ResourceAlreadyRegistered("error.resource.nameTaken");
        }
    }

    /**
     * Saves or updates a resource on the database
     *
     * @param r resource to be resource or updated
     */
    public void saveOrUpdate(Resource r) {
        resourceDao.editOrSave(r);
    }

    /**
     * Updates a resource on the database
     *
     * @param r resource to be updated
     */
    public void edit(Resource r) {
        resourceDao.edit(r);
    }

    /**
     * Deletes a resource from the database
     *
     * @param id The id of the resource to be deleted from the database
     */
    public void delete(String id) {
        resourceDao.delete(id);
    }

    /**
     * Searches for the Resource with the given id
     *
     * @param id The id of the requested Resource
     * @return The Resource or null if it wasn't found
     */
    public Resource findById(String id) {
        return resourceDao.findById(id);
    }

    /**
     * Searches for the resource with the given name
     *
     * @param name The name of the requested resource
     * @return The resource or null if it wasn't found
     */
    public Resource findByName(String name) {
        return resourceDao.findByName(name);
    }

    /**
     * Returns all the resource on the database
     *
     * @return The list of resource
     */
    public List findAll() {
        return resourceDao.getAllResources();
    }

    /**
     * Returns all the resource on the database that match with the resource type
     *
     * @param resourceType The resource type of the requested resources
     * @return The list of resources
     */
    public List findByResourceType(String resourceType) {
        return resourceDao.findByResourceType(resourceType);
    }

    public List<Resource> findBylikeName(String nombreResource) {
        return resourceDao.findByLikeName(nombreResource);
    }
}
