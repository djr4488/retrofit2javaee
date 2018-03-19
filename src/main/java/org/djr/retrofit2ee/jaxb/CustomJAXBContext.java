package org.djr.retrofit2ee.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public interface CustomJAXBContext {
    JAXBContext getJAXBContext() throws JAXBException;
}
