package org.siorven.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ander on 25/05/2017.
 */
public interface IResourceContainer {

    Slot findSlot(Map<String, Object> position);

    Map<String, Class> getPositionParams();

}
