package org.djr.retrofit2ee;

import org.djr.retrofit2ee.jackson.JsonRetrofitProducer;
import org.djr.retrofit2ee.jackson.RetrofitJson;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import retrofit2.Retrofit;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({JsonRetrofitProducer.class, RetrofitProducer.class})
public class RxJavaInjectTest {
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.NONE,
            createAsync = false)
    private Retrofit rxJavaNoSchedulerCreate;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.NONE,
            createAsync = true)
    private Retrofit rxJavaNoSchedulerCreateAsync;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.COMPUTATION,
            createAsync = false)
    private Retrofit rxJavaComputationScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.IO,
            createAsync = false)
    private Retrofit rxJavaIOScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.NEW_THREAD,
            createAsync = false)
    private Retrofit rxJavaNewThreadScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.RXJAVA1_IMMEDIATE,
            createAsync = false)
    private Retrofit rxJavaImmediateScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.TRAMPOLINE,
            createAsync = false)
    private Retrofit rxJavaTrampolineScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA,
            schedulerType = SchedulerType.FROM,
            createAsync = false)
    private Retrofit rxJavaFromScheduler;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    @Produces
    @Mock(name = "JsonRetrofitExecutorService")
    private ManagedExecutorService executorService;

    public RxJavaInjectTest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("JSON.noTrafficLogging", "FALSE");
    }

    @Test
    public void testAllInjectionsNotNull() {
        assertNotNull(rxJavaComputationScheduler);
        assertNotNull(rxJavaIOScheduler);
        assertNotNull(rxJavaNewThreadScheduler);
        assertNotNull(rxJavaNoSchedulerCreate);
        assertNotNull(rxJavaNoSchedulerCreateAsync);
        assertNotNull(rxJavaImmediateScheduler);
        assertNotNull(rxJavaTrampolineScheduler);
        assertNotNull(rxJavaFromScheduler);
    }
}
