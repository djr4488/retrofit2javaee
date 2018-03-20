package org.djr.retrofit2ee.guava;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.RetrofitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit.converter.guava.GuavaOptionalConverterFactory;
import retrofit2.Retrofit;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Properties;

public class GuavaRetrofitProducer {
    private static Logger log = LoggerFactory.getLogger(GuavaRetrofitProducer.class);
    @Inject
    @RetrofitProperties
    private Properties properties;

    @Produces
    @RetrofitGuava
    public Retrofit getClient(InjectionPoint injectionPoint) {
        RetrofitGuava guavaClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitGuava.class);
        log.debug("getClient() injecting retrofit guava client with annotation:{}", guavaClientConfig);
        String baseUrlPropertyName = guavaClientConfig.baseUrlPropertyName();
        String captureTrafficLogsPropertyName = guavaClientConfig.captureTrafficLogsPropertyName();
        String baseUrl = properties.getProperty(baseUrlPropertyName);
        Boolean enableTrafficLogging =
                Boolean.parseBoolean(properties.getProperty(captureTrafficLogsPropertyName, "FALSE"));
        return getTransport(baseUrl, enableTrafficLogging);
    }

    private Retrofit getTransport(String baseUrl, boolean enableTrafficLogging) {
        log.debug("getTransport() baseUrl:{}, enableTrafficLogging:{}", baseUrl, enableTrafficLogging);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        setLoggingInterceptor(enableTrafficLogging, httpClient);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GuavaOptionalConverterFactory.create())
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
