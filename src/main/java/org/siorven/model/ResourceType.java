package org.siorven.model;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Created by ander on 26/05/2017.
 */
@XmlEnum
public enum ResourceType {
    POWDER {
        @Override
        public String toString() {
            return "resourceType.powder";
        }
    },
    LIQUID {
        @Override
        public String toString() {
            return "resourceType.liquid";
        }
    },
    HOT_LIQUID {
        @Override
        public String toString() {
            return "resourceType.hotLiquid";
        }
    },
    COLD_LIQUID {
        @Override
        public String toString() {
            return "resourceType.coldLiquid";
        }
    },
    ITEM {
        @Override
        public String toString() {
            return "resourceType.item";
        }
    },
    COLD_ITEM {
        @Override
        public String toString() {
            return "resourceType.coldItem";
        }
    },
    HOT_ITEM {
        @Override
        public String toString() {
            return "resourceType.hotItem";
        }
    };

    public static Unit unit(ResourceType type) {
        switch (type) {
            case POWDER:
                return Unit.G;
            case LIQUID:
            case HOT_LIQUID:
            case COLD_LIQUID:
                return Unit.ML;
            case ITEM:
            case COLD_ITEM:
            case HOT_ITEM:
            default:
                return Unit.U;
        }
    }

}
