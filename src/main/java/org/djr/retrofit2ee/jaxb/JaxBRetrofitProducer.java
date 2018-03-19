package org.djr.retrofit2ee.jaxb;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.BeanLookupUtil;
import org.djr.retrofit2ee.RetrofitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Properties;

public class JaxBRetrofitProducer {
    private static Logger log = LoggerFactory.getLogger(JaxBRetrofitProducer.class);
    @Inject
    @RetrofitProperties
    private Properties properties;

    @Produces
    @RetrofitJaxB
    public Retrofit getClient(InjectionPoint injectionPoint) {
        RetrofitJaxB jaxbClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitJaxB.class);
        log.debug("getClient() injecting retrofit JAXB client with annotation:{}", jaxbClientConfig);
        String baseUrlPropertyName = jaxbClientConfig.baseUrlPropertyName();
        String captureTrafficLogsPropertyName = jaxbClientConfig.captureTrafficLogsPropertyName();
        String baseUrl = properties.getProperty(baseUrlPropertyName);
        Boolean enableTrafficLogging =
                Boolean.parseBoolean(properties.getProperty(captureTrafficLogsPropertyName, "FALSE"));
        return getTransport(baseUrl, enableTrafficLogging, jaxbClientConfig.customJAXBContextName());
    }

    private Retrofit getTransport(String baseUrl, boolean enableTrafficLogging, String customContextName) {
        log.debug("getTransport() baseUrl:{}, enableTrafficLogging:{}", baseUrl, enableTrafficLogging);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        setLoggingInterceptor(enableTrafficLogging, httpClient);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        if ("".equalsIgnoreCase(customContextName.trim())) {
            retrofitBuilder.addConverterFactory(JaxbConverterFactory.create());
        } else {
            try {
                retrofitBuilder.addConverterFactory(
                        JaxbConverterFactory.create(getBeanByNameOfClass(customContextName, CustomJAXBContext.class).getJAXBContext()));
            } catch (Exception ex) {
                throw new JAXBRetrofitException("Failed to find CustomJAXBContext for name:" + customContextName);
            }
        }
        retrofitBuilder.baseUrl(baseUrl);
        retrofitBuilder.client(httpClient.build());
        return retrofitBuilder.build();
    }

    private void setLoggingInterceptor(boolean enableTrafficLogging, OkHttpClient.Builder builder) {
        if (enableTrafficLogging) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
    }

    /**
     * Method to help classes not managed by CDI to get CDI managed beans
     * @param name name of the bean you are looking for; should be annotated with @Named("beanName")
     * @param clazz the class type you are looking for BeanName.class
     * @param <T> generics
     * @return instance of BeanName.class
     * @throws Exception if something goes horribly wrong I suppose it could get thrown
     */
    private <T> T getBeanByNameOfClass(String name, Class<T> clazz)
            throws Exception {
        return BeanLookupUtil.getBeanByNameOfClass(name, clazz);
    }
}
