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
public class RxJava2InjectionTest {
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.NONE,
            createAsync = false)
    private Retrofit rxJava2NoSchedulerCreate;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.NONE,
            createAsync = true)
    private Retrofit rxJava2NoSchedulerCreateAsync;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.COMPUTATION,
            createAsync = false)
    private Retrofit rxJava2ComputationScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.IO,
            createAsync = false)
    private Retrofit rxJava2IOScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.NEW_THREAD,
            createAsync = false)
    private Retrofit rxJava2NewThreadScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.RXJAVA2_SINGLE,
            createAsync = false)
    private Retrofit rxJava2SingleScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.RXJAVA2,
            schedulerType = SchedulerType.TRAMPOLINE,
            createAsync = false)
    private Retrofit rxJava2TrampolineScheduler;

    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
                 baseUrlPropertyName = "JSON.baseUrlPropertyName",
                 asyncAdapterType = AsyncAdapterType.RXJAVA2,
                 schedulerType = SchedulerType.FROM,
                 createAsync = false)
    private Retrofit rxJava2FromScheduler;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();
    @Produces
    @Mock(name = "JsonRetrofitExecutorService")
    private ManagedExecutorService executorService;

    public RxJava2InjectionTest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("JSON.noTrafficLogging", "FALSE");
    }

    @Test
    public void testAllInjectionsNotNull() {
        assertNotNull(rxJava2ComputationScheduler);
        assertNotNull(rxJava2IOScheduler);
        assertNotNull(rxJava2NewThreadScheduler);
        assertNotNull(rxJava2NoSchedulerCreate);
        assertNotNull(rxJava2NoSchedulerCreateAsync);
        assertNotNull(rxJava2SingleScheduler);
        assertNotNull(rxJava2TrampolineScheduler);
        assertNotNull(rxJava2FromScheduler);
    }
}
