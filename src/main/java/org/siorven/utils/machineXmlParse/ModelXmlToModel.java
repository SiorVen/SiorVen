package org.siorven.utils.machineXmlParse;

import org.siorven.model.*;
import org.siorven.model.ResourceType;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

/**
 * Created by ander on 29/05/2017.
 */
public class ModelXmlToModel {

    public static MachineModel Xml2Model(MultipartFile file) {
        MachineModelXml modelXml = readXmlObject(file);
        assert modelXml != null : "Model should not be null at this point";
        return parseXmlModel(modelXml);
    }

    private static MachineModel parseXmlModel(MachineModelXml modelXml) {
        MachineModel model = new MachineModel();
        model.setManufacturer(modelXml.getManufacturer());
        model.setReference(modelXml.getModelReference());
        for (Object ditrib : modelXml.getDistributions().getMatrixOrCompartiments()) {

            if (ditrib.getClass() == MatrixDistrubutionXml.class) {
                MatrixDistrubutionXml matrix = (MatrixDistrubutionXml) ditrib;
                MatrixDistribution matrixDistrubution = new MatrixDistribution(matrix.getName(), matrix.getCols(), matrix.getCols());
                for (int i = 0; i < matrix.getCols() * matrix.getRows(); i++) {
                    Slot slot = new Slot();
                    slot.setUnit(ResourceType.unit(ResourceType.valueOf(matrix.getResourceTypes().resourceType.get(0).value())));
                    slot.setName((i / matrix.getCols()) + ":" + (i % matrix.getCols())); //Format will be "col:row" ("x:y")
                    slot.setCapacity(matrix.getCapacity().intValue());
                    matrixDistrubution.getSlots().add(slot);
                }
                model.getAviableDistributions().add(matrixDistrubution);
            }
            if (ditrib.getClass() == CompartimentDistributionXml.class) {
                CompartimentDistributionXml compartiments = (CompartimentDistributionXml) ditrib;
                CompartimentDistribution compartimentDistribution = new CompartimentDistribution(compartiments.getName(), compartiments.getCompartiment().size());
                for (int i = 0; i < compartiments.getCompartiment().size(); i++) {
                    CompartimentXml compartimentXml = compartiments.getCompartiment().get(i);
                    Slot slot = new Slot();
                    slot.setName(Integer.toString(i));
                    slot.setCapacity(compartimentXml.getCapacity().intValue());
                    slot.setUnit(ResourceType.unit(ResourceType.valueOf(compartimentXml.getResourceTypes().resourceType.get(0).value())));
                    compartimentDistribution.getSlots().add(slot);
                }
                model.getAviableDistributions().add(compartimentDistribution);
            }
        }
        return model;
    }

    private static MachineModelXml readXmlObject(MultipartFile file) {
        try {
            JAXBContext context = JAXBContext.newInstance(MachineModelXml.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (MachineModelXml) unmarshaller.unmarshal(file.getInputStream());
        } catch (JAXBException | IOException e) {
            return null;
        }
    }

}
