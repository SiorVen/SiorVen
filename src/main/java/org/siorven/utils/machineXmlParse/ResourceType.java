
package org.siorven.utils.machineXmlParse;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for resourceType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="resourceType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="POWDER"/>
 *     &lt;enumeration value="LIQUID"/>
 *     &lt;enumeration value="HOT_LIQUID"/>
 *     &lt;enumeration value="COLD_LIQUID"/>
 *     &lt;enumeration value="ITEM"/>
 *     &lt;enumeration value="COLD_ITEM"/>
 *     &lt;enumeration value="HOT_ITEM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "resourceType")
@XmlEnum
public enum ResourceType {

    POWDER,
    LIQUID,
    HOT_LIQUID,
    COLD_LIQUID,
    ITEM,
    COLD_ITEM,
    HOT_ITEM;

    public String value() {
        return name();
    }

    public static ResourceType fromValue(String v) {
        return valueOf(v);
    }

}
