package org.siorven.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.servlet.ServletContext;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

/**
 * Created by ander on 27/05/2017.
 */


@Service
public class XmlValidationService {

   private Validator validator;

   @Autowired
    public XmlValidationService(ServletContext context){
        File schemaFile = new File(context.getRealPath("/rsc/xsd/machine.xsd"));
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            validator = schemaFactory.newSchema(schemaFile).newValidator();
        } catch (SAXException e) {
            validator = null;
        }
        assert validator != null : "Validator initialization failed";
    }

    public void ValidateMachine(StreamSource machineModel) throws SAXParseException {
        try {
            validator.validate(machineModel);
        } catch (SAXParseException spe){
            throw spe;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

}
