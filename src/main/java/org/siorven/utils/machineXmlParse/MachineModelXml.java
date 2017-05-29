
package org.siorven.utils.machineXmlParse;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for machineModelXml complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="machineModelXml">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="modelReference" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="products">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="distributions">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;choice maxOccurs="unbounded">
 *                   &lt;element ref="{}matrix"/>
 *                   &lt;element ref="{}compartiments"/>
 *                 &lt;/choice>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "model")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "machineModelXml", propOrder = {
    "manufacturer",
    "modelReference",
    "products",
    "distributions"
})
public class MachineModelXml {

    @XmlElement(required = true)
    protected String manufacturer;
    @XmlElement(required = true)
    protected String modelReference;
    @XmlElement(required = true)
    protected MachineModelXml.Products products;
    @XmlElement(required = true)
    protected MachineModelXml.Distributions distributions;

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the modelReference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelReference() {
        return modelReference;
    }

    /**
     * Sets the value of the modelReference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelReference(String value) {
        this.modelReference = value;
    }

    /**
     * Gets the value of the products property.
     * 
     * @return
     *     possible object is
     *     {@link MachineModelXml.Products }
     *     
     */
    public MachineModelXml.Products getProducts() {
        return products;
    }

    /**
     * Sets the value of the products property.
     * 
     * @param value
     *     allowed object is
     *     {@link MachineModelXml.Products }
     *     
     */
    public void setProducts(MachineModelXml.Products value) {
        this.products = value;
    }

    /**
     * Gets the value of the distributions property.
     * 
     * @return
     *     possible object is
     *     {@link MachineModelXml.Distributions }
     *     
     */
    public MachineModelXml.Distributions getDistributions() {
        return distributions;
    }

    /**
     * Sets the value of the distributions property.
     * 
     * @param value
     *     allowed object is
     *     {@link MachineModelXml.Distributions }
     *     
     */
    public void setDistributions(MachineModelXml.Distributions value) {
        this.distributions = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;choice maxOccurs="unbounded">
     *         &lt;element ref="{}matrix"/>
     *         &lt;element ref="{}compartiments"/>
     *       &lt;/choice>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "matrixOrCompartiments"
    })
    public static class Distributions {

        @XmlElements({
            @XmlElement(name = "matrix", type = MatrixDistrubutionXml.class),
            @XmlElement(name = "compartiments", type = CompartimentDistributionXml.class)
        })
        protected List<Object> matrixOrCompartiments;

        /**
         * Gets the value of the matrixOrCompartiments property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the matrixOrCompartiments property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMatrixOrCompartiments().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MatrixDistrubutionXml }
         * {@link CompartimentDistributionXml }
         * 
         * 
         */
        public List<Object> getMatrixOrCompartiments() {
            if (matrixOrCompartiments == null) {
                matrixOrCompartiments = new ArrayList<Object>();
            }
            return this.matrixOrCompartiments;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "product"
    })
    public static class Products {

        @XmlElement(required = true)
        protected List<String> product;

        /**
         * Gets the value of the product property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the product property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProduct().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getProduct() {
            if (product == null) {
                product = new ArrayList<String>();
            }
            return this.product;
        }

    }

}
