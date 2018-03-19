package org.djr.retrofit2ee.wire;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.RetrofitProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.wire.WireConverterFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Properties;

public class WireRetrofitProducer {
    private static Logger log = LoggerFactory.getLogger(WireRetrofitProducer.class);
    @Inject
    @RetrofitProperties
    private Properties properties;

    @Produces
    @RetrofitWire
    public Retrofit getClient(InjectionPoint injectionPoint) {
        RetrofitWire wireClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitWire.class);
        log.debug("getClient() injecting retrofit wire client with annotation:{}", wireClientConfig);
        String baseUrlPropertyName = wireClientConfig.baseUrlPropertyName();
        String captureTrafficLogsPropertyName = wireClientConfig.captureTrafficLogsPropertyName();
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
                .addConverterFactory(WireConverterFactory.create())
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
