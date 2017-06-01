package org.siorven.utils.machineXmlParse;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for compartimentXml complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="compartimentXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resourceTypes" type="{}resourceTypes"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="capacity" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compartimentXml", propOrder = {
        "resourceTypes"
})
public class CompartimentXml {

    @XmlElement(required = true)
    protected ResourceTypes resourceTypes;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "capacity", required = true)
    protected BigInteger capacity;

    /**
     * Gets the value of the resourceTypes property.
     *
     * @return possible object is
     * {@link ResourceTypes }
     */
    public ResourceTypes getResourceTypes() {
        return resourceTypes;
    }

    /**
     * Sets the value of the resourceTypes property.
     *
     * @param value allowed object is
     *              {@link ResourceTypes }
     */
    public void setResourceTypes(ResourceTypes value) {
        this.resourceTypes = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the capacity property.
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getCapacity() {
        return capacity;
    }

    /**
     * Sets the value of the capacity property.
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setCapacity(BigInteger value) {
        this.capacity = value;
    }

}
