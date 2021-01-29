package entity.impl;

import entity.Result;
import entity.ResultWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XmlWriterImpl implements ResultWriter {
    private static final Logger LOGGER = LogManager.getLogger(XmlWriterImpl.class);
    private static final String TARGET_PATH = "target/result.xml";

    @Override
    public void writeResult(Result result) {
        try {
            File file = new File(TARGET_PATH);
            JAXBContext jaxbContext = JAXBContext.newInstance(Result.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(result, file);
        } catch (JAXBException e) {
            LOGGER.error("Something is wrong at JAXB!!!", e);
        }
    }
}