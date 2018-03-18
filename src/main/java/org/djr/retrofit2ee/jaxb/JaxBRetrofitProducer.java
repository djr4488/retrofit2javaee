package org.djr.retrofit2ee.jaxb;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.AsyncAdapterType;
import org.djr.retrofit2ee.RetrofitProperties;
import org.djr.retrofit2ee.SchedulerType;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
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
    public Retrofit getClient(InjectionPoint injectionPoint)
            throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        RetrofitJaxB jaxbClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitJaxB.class);
        log.debug("getClient() injecting retrofit JAXB client with annotation:{}", jaxbClientConfig);
        String baseUrlPropertyName = jaxbClientConfig.baseUrlPropertyName();
        String captureTrafficLogsPropertyName = jaxbClientConfig.captureTrafficLogsPropertyName();
        String baseUrl = properties.getProperty(baseUrlPropertyName);
        Boolean enableTrafficLogging =
                Boolean.parseBoolean(properties.getProperty(captureTrafficLogsPropertyName, "FALSE"));
        return getTransport(baseUrl, enableTrafficLogging, jaxbClientConfig.asyncAdapterType(), jaxbClientConfig.schedulerType(),
                jaxbClientConfig.createAsync());
    }

    private Retrofit getTransport(String baseUrl, boolean enableTrafficLogging, AsyncAdapterType asyncAdapterType,
                                  SchedulerType schedulerType, boolean createAsync) {
        log.debug("getTransport() baseUrl:{}, enableTrafficLogging:{}", baseUrl, enableTrafficLogging);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        setLoggingInterceptor(enableTrafficLogging, httpClient);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(JaxbConverterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build());
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
}
