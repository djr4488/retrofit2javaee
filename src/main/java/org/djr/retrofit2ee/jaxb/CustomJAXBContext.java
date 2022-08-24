package org.djr.retrofit2ee.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public interface CustomJAXBContext {
    JAXBContext getJAXBContext() throws JAXBException;
    Marshaller createMarshaller() throws Exception;
    Unmarshaller createUnmarshaller() throws Exception;
}
