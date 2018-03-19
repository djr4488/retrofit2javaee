package org.djr.retrofit2ee.gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.AdapterUtils;
import org.djr.retrofit2ee.AsyncAdapterType;
import org.djr.retrofit2ee.RetrofitProperties;
import org.djr.retrofit2ee.SchedulerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import java.util.Properties;

public class GsonRetrofitProducer {
    private static Logger log = LoggerFactory.getLogger(GsonRetrofitProducer.class);
    @Inject
    @RetrofitProperties
    private Properties properties;
    @Resource(name = "RetrofitManagedExecutorService")
    private ManagedExecutorService managedExecutorService;

    @Produces
    @RetrofitGson
    public Retrofit getClient(InjectionPoint injectionPoint) {
        RetrofitGson gsonClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitGson.class);
        log.trace("getClient() injecting retrofit gson client with annotation:{}", gsonClientConfig);
        String baseUrlPropertyName = gsonClientConfig.baseUrlPropertyName();
        String captureTrafficLogsPropertyName = gsonClientConfig.captureTrafficLogsPropertyName();
        String baseUrl = properties.getProperty(baseUrlPropertyName);
        Boolean enableTrafficLogging =
                Boolean.parseBoolean(properties.getProperty(captureTrafficLogsPropertyName, "FALSE"));
        return getTransport(baseUrl, enableTrafficLogging, gsonClientConfig.asyncAdapterType(), gsonClientConfig.schedulerType(),
                gsonClientConfig.createAsync());
    }

    private Retrofit getTransport(String baseUrl, boolean enableTrafficLogging, AsyncAdapterType asyncAdapterType,
                                  SchedulerType schedulerType, boolean createAsync) {
        log.debug("getTransport() baseUrl:{}, enableTrafficLogging:{}", baseUrl, enableTrafficLogging);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        setLoggingInterceptor(enableTrafficLogging, httpClient);
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .client(httpClient.build());
        AdapterUtils.setCallAdapter(asyncAdapterType, schedulerType, retrofitBuilder, createAsync, managedExecutorService);
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
