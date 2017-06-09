package org.siorven.model.interfaces;

import org.siorven.model.Slot;

import java.util.Map;

/**
 * Enforces the implementor to provide means of mapping slots with parameters and defining which and what type
 * those parameters are
 */
public interface IResourceContainer {

    /**
     * Get the slot with the parameters
     *
     * @param position The Map containing the parameters
     * @return The Slot if found
     */
    Slot findSlot(Map<String, Object> position);

    /**
     * Sould return the parameter names and their types in a map
     *
     * @return The <paramName, Type.class>
     */
    Map<String, Class> getPositionParams();

}
