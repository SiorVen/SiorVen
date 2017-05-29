
package org.siorven.utils.machineXmlParse;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.siorven.utils.machineXmlParse package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResourceTypes_QNAME = new QName("", "resourceTypes");
    private final static QName _Compartiment_QNAME = new QName("", "compartiment");
    private final static QName _Compartiments_QNAME = new QName("", "compartiments");
    private final static QName _Model_QNAME = new QName("", "model");
    private final static QName _Distribution_QNAME = new QName("", "distribution");
    private final static QName _Matrix_QNAME = new QName("", "matrix");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.siorven.utils.machineXmlParse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MachineModelXml }
     * 
     */
    public MachineModelXml createMachineModelXml() {
        return new MachineModelXml();
    }

    /**
     * Create an instance of {@link ResourceTypes }
     * 
     */
    public ResourceTypes createResourceTypes() {
        return new ResourceTypes();
    }

    /**
     * Create an instance of {@link CompartimentXml }
     * 
     */
    public CompartimentXml createCompartimentXml() {
        return new CompartimentXml();
    }

    /**
     * Create an instance of {@link CompartimentDistributionXml }
     * 
     */
    public CompartimentDistributionXml createCompartimentDistributionXml() {
        return new CompartimentDistributionXml();
    }

    /**
     * Create an instance of {@link DistributionXml }
     * 
     */
    public DistributionXml createDistributionXml() {
        return new DistributionXml();
    }

    /**
     * Create an instance of {@link MatrixDistrubutionXml }
     * 
     */
    public MatrixDistrubutionXml createMatrixDistrubutionXml() {
        return new MatrixDistrubutionXml();
    }

    /**
     * Create an instance of {@link MachineModelXml.Products }
     * 
     */
    public MachineModelXml.Products createMachineModelXmlProducts() {
        return new MachineModelXml.Products();
    }

    /**
     * Create an instance of {@link MachineModelXml.Distributions }
     * 
     */
    public MachineModelXml.Distributions createMachineModelXmlDistributions() {
        return new MachineModelXml.Distributions();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResourceTypes }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "resourceTypes")
    public JAXBElement<ResourceTypes> createResourceTypes(ResourceTypes value) {
        return new JAXBElement<ResourceTypes>(_ResourceTypes_QNAME, ResourceTypes.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompartimentXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "compartiment")
    public JAXBElement<CompartimentXml> createCompartiment(CompartimentXml value) {
        return new JAXBElement<CompartimentXml>(_Compartiment_QNAME, CompartimentXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompartimentDistributionXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "compartiments")
    public JAXBElement<CompartimentDistributionXml> createCompartiments(CompartimentDistributionXml value) {
        return new JAXBElement<CompartimentDistributionXml>(_Compartiments_QNAME, CompartimentDistributionXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MachineModelXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "model")
    public JAXBElement<MachineModelXml> createModel(MachineModelXml value) {
        return new JAXBElement<MachineModelXml>(_Model_QNAME, MachineModelXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DistributionXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "distribution")
    public JAXBElement<DistributionXml> createDistribution(DistributionXml value) {
        return new JAXBElement<DistributionXml>(_Distribution_QNAME, DistributionXml.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MatrixDistrubutionXml }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "matrix")
    public JAXBElement<MatrixDistrubutionXml> createMatrix(MatrixDistrubutionXml value) {
        return new JAXBElement<MatrixDistrubutionXml>(_Matrix_QNAME, MatrixDistrubutionXml.class, null, value);
    }

}
