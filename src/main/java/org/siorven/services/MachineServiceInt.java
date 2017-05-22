package org.siorven.services;

import org.siorven.logic.Configuration;
import org.siorven.model.Product;
import org.siorven.model.Resource;
import org.siorven.model.Slot;

import java.util.HashMap;

/**
 * Created by Andoni on 22/05/2017.
 */
public interface MachineServiceInt {

    public int editConfiguration(Configuration c);
    public int deleteMachine(int id);
    public int advancedReposition(HashMap<Resource, Slot> ResourceMap, HashMap<Product, Integer> productMap);
}
