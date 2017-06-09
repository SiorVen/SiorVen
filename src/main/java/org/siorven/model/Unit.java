package org.siorven.model;

/**
 * Represents a unit
 */
public enum Unit {

    /**
     * Grams - Weight
     */
    G { //Weight

        @Override
        public String toString() {
            return "unit.grams";
        }
    },
    /**
     * Millilitres - Volume
     */
    ML { //Volume

        @Override
        public String toString() {
            return "unit.milliliters";
        }
    },
    /**
     * Unit - Elements
     */
    U { //Units

        @Override
        public String toString() {
            return "unit.units";
        }
    }
}
