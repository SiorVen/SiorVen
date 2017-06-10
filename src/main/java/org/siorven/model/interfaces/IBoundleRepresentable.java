package org.siorven.model.interfaces;

/**
 * The implementor should contain a key that is present on a resource variable in order to internationalize
 * its representation
 */
@FunctionalInterface
public interface IBoundleRepresentable {
    /**
     * Should contain the key to the bundle message containing the implementors representation
     *
     * @return The key
     */
    String geyClassKey();
}
