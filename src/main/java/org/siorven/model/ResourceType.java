package org.siorven.model;

/**
 * Created by ander on 26/05/2017.
 */
public enum ResourceType {
    POWDER{
        @Override
        public String toString() {
            return "resourceType.powder";
        }
    },
    LIQUID{
        @Override
        public String toString() {
            return "resourceType.liquid";
        }
    },
    HOT_LIQUID{
        @Override
        public String toString() {
            return "resourceType.hotLiquid";
        }
    },
    COLD_LIQUID{
        @Override
        public String toString() {
            return "resourceType.coldLiquid";
        }
    },
    ITEM{
        @Override
        public String toString() {
            return "resourceType.item";
        }
    },
    COLD_ITEM{
        @Override
        public String toString() {
            return "resourceType.coldItem";
        }
    },
    HOT_ITEM{
        @Override
        public String toString() {
            return "resourceType.hotItem";
        }
    }

}
