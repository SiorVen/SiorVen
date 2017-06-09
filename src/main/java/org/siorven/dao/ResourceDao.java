package org.siorven.dao;

import org.siorven.model.Resource;
import org.siorven.model.validacion.PersistenceGroup;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Created by Andoni on 24/05/2017.
 */
public interface ResourceDao {

    /**
     * Persists a Resource
     *
     * @param r The resource to be persisted
     */
    void save(@Validated(PersistenceGroup.class) Resource r);

    /**
     * Updates a persisted Resource
     *
     * @param r The Resource to be persisted
     */
    void edit(@Validated(PersistenceGroup.class) Resource r);

    /**
     * Updates or persists a Resource
     *
     * @param r The Resource to be persisted
     */
    void editOrSave(@Validated(PersistenceGroup.class) Resource r);

    /**
     * Deletes a persisted Resource
     *
     * @param id The id of the Resource to be deleted
     */
    void delete(String id);

    /**
     * Finds a persisted Resource by its id
     *
     * @param id The id of the Resource to be searched
     * @return The Resource or null if it wasn't found
     */
    Resource findById(String id);

    /**
     * Finds a persisted Resource by its name
     *
     * @param name The name of the Resource to be searched
     * @return The Resource or null if it wasn't found
     */
    Resource findByName(String name);

    /**
     * Returns all the persisted Resources
     *
     * @return A {@link List} conta1ining all the Resources
     */
    List<Resource> getAllResources();

    /**
     * Finds persisted resources by their resource type
     *
     * @param resourceType The resource type of the resource to be searched
     * @return The list of resources with that resource type
     */
    List<Resource> findByResourceType(String resourceType);

    /**
     * Finds persisted resources by their resource name applying a %name% pattern
     *
     * @param resourceName The resource type of the resource to be searched
     * @return The list of resources
     */
    List<Resource> findByLikeName(String resourceName);
}
