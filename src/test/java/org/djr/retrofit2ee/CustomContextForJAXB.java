package org.djr.retrofit2ee;

import org.djr.retrofit2ee.jaxb.CustomJAXBContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

@ApplicationScoped
@Named("testCustomJAXBContext")
public class CustomContextForJAXB implements CustomJAXBContext {
    private JAXBContext context;

    public JAXBContext getJAXBContext()
    throws JAXBException {
        context = JAXBContext.newInstance(Response.class);
        return context;
    }

    public Marshaller createMarshaller() throws Exception {
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.FALSE);
        return marshaller;
    }

    public Unmarshaller createUnmarshaller() throws Exception {
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        return unmarshaller;
    }
}
