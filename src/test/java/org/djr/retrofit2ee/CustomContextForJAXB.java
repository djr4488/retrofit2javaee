package org.djr.retrofit2ee;

import org.djr.retrofit2ee.Response;
import org.djr.retrofit2ee.jaxb.CustomJAXBContext;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

@ApplicationScoped
@Named("testCustomJAXBContext")
public class CustomContextForJAXB implements CustomJAXBContext {
    private JAXBContext context;

    public JAXBContext getJAXBContext()
    throws JAXBException {
        context = JAXBContext.newInstance(Response.class);
        return context;
    }
}
