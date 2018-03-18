package org.djr.retrofit2ee;

import org.djr.retrofit2ee.jackson.JsonRetrofitProducer;
import org.djr.retrofit2ee.jackson.RetrofitJson;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import retrofit2.Retrofit;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({JsonRetrofitProducer.class, RetrofitProducer.class})
public class GuavaJava8AndScalaInjectTest {
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.GUAVA,
            schedulerType = SchedulerType.NONE,
            createAsync = false)
    private Retrofit guavaInject;
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.JAVA8,
            schedulerType = SchedulerType.NONE,
            createAsync = false)
    private Retrofit java8Inject;
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.noTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName",
            asyncAdapterType = AsyncAdapterType.SCALA,
            schedulerType = SchedulerType.NONE,
            createAsync = false)
    private Retrofit scalaInject;
    @Produces
    @RetrofitProperties
    private Properties properties = new Properties();

    public GuavaJava8AndScalaInjectTest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("JSON.noTrafficLogging", "FALSE");
    }

    @Test
    public void testInjectedNotNull() {
        assertNotNull(guavaInject);
        assertNotNull(java8Inject);
        assertNotNull(scalaInject);
    }
}
