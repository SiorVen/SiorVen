package org.siorven.services;

import org.siorven.model.Product;
import org.siorven.model.Resource;
import org.siorven.model.Slot;

import java.util.Map;

/**
 * Created by Andoni on 22/05/2017.
 */
public interface RepositionServiceInt {

    public int simpleReposition();

    public int resourcesReposition(Map<Resource, Slot> resourceMap);

    public int advancedReposition(Map<Resource, Slot> ResourceMap, Map<Product, Integer> productMap);
}
