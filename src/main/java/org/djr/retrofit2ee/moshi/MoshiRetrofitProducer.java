package org.djr.retrofit2ee.moshi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.RetrofitProperties;
import org.djr.retrofit2ee.RetrofitPropertyLoader;
import org.djr.retrofit2ee.protobuf.ProtobufRetrofitProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Properties;

public class MoshiRetrofitProducer {
    private static Logger log = LoggerFactory.getLogger(ProtobufRetrofitProducer.class);
    @Inject
    @RetrofitProperties
    private Properties properties;

    @Produces
    @RetrofitMoshi
    public Retrofit getClient(InjectionPoint injectionPoint)
            throws NoSuchFieldException, InstantiationException, IllegalAccessException {
        RetrofitMoshi xmlClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitMoshi.class);
        log.debug("getClient() injecting retrofit xml client with annotation:{}", xmlClientConfig);
        String baseUrlPropertyName = xmlClientConfig.baseUrlPropertyName();
        String captureTrafficLogsPropertyName = xmlClientConfig.captureTrafficLogsPropertyName();
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
                .addConverterFactory(MoshiConverterFactory.create())
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
