package org.djr.retrofit2ee;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

public class BeanLookupUtil {
    /**
     * Method to help classes not managed by CDI to get CDI managed beans
     * @param name name of the bean you are looking for; should be annotated with @Named("beanName")
     * @param clazz the class type you are looking for BeanName.class
     * @param <T> generics
     * @return instance of BeanName.class
     * @throws Exception if something goes horribly wrong I suppose it could get thrown
     */
    public static <T> T getBeanByNameOfClass(String name, Class<T> clazz)
            throws Exception {
        BeanManager bm = CDI.current().getBeanManager();
        Bean<T> bean = (Bean<T>) bm.getBeans(name).iterator().next();
        CreationalContext<T> ctx = bm.createCreationalContext(bean);
        return (T) bm.getReference(bean, clazz, ctx);
    }
}
