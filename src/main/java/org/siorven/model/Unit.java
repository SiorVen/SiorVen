package org.siorven.model;

/**
 * Created by ander on 26/05/2017.
 */
public enum Unit {

    G { //Weight

        @Override
        public String toString() {
            return "unit.grams";
        }
    }, ML { //Volume

        @Override
        public String toString() {
            return "unit.milliliters";
        }
    }, U { //Units

        @Override
        public String toString() {
            return "unit.units";
        }
    }
}
