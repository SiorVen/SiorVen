
package org.siorven.utils.machineXmlParse;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for compartimentDistributionXml complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="compartimentDistributionXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}compartiment" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compartimentDistributionXml", propOrder = {
    "compartiment"
})
public class CompartimentDistributionXml {

    @XmlElement(required = true)
    protected List<CompartimentXml> compartiment;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the compartiment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compartiment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompartiment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompartimentXml }
     * 
     * 
     */
    public List<CompartimentXml> getCompartiment() {
        if (compartiment == null) {
            compartiment = new ArrayList<CompartimentXml>();
        }
        return this.compartiment;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
