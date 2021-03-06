package org.djr.retrofit2ee;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.djr.retrofit2ee.gson.GsonRetrofitProducer;
import org.djr.retrofit2ee.gson.RetrofitGson;
import org.djr.retrofit2ee.jackson.*;
import org.djr.retrofit2ee.jaxb.JaxBRetrofitProducer;
import org.djr.retrofit2ee.jaxb.RetrofitJaxB;
import org.djr.retrofit2ee.moshi.MoshiRetrofitProducer;
import org.djr.retrofit2ee.moshi.RetrofitMoshi;
import org.djr.retrofit2ee.protobuf.ProtobufRetrofitProducer;
import org.djr.retrofit2ee.protobuf.RetrofitProtobuf;
import org.djr.retrofit2ee.scalar.RetrofitScalar;
import org.djr.retrofit2ee.scalar.ScalarsRetrofitProducer;
import org.djr.retrofit2ee.xml.RetrofitXml;
import org.djr.retrofit2ee.xml.XmlRetrofitProducer;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import java.io.IOException;
import java.util.Properties;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(CdiRunner.class)
@AdditionalClasses({GsonRetrofitProducer.class, ProtobufRetrofitProducer.class, XmlRetrofitProducer.class,
        JsonRetrofitProducer.class, RetrofitProducer.class, MoshiRetrofitProducer.class, JaxBRetrofitProducer.class,
        CustomContextForJAXB.class, ScalarsRetrofitProducer.class})
public class Retrofit2EETest {
    private static Logger log = LoggerFactory.getLogger(Retrofit2EETest.class);

    @JacksonModule(jacksonModules = {com.fasterxml.jackson.datatype.jsr310.JavaTimeModule.class})
    @JacksonMapperFeature(features = {
            @MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
            @MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
    @JacksonSerializationFeature(features = {
            @SerializationFeatureConfig(feature = SerializationFeature.INDENT_OUTPUT, value = true)})
    @JacksonDeserializationFeature(features = {
            @DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "JSON.enableTrafficLogging",
            baseUrlPropertyName = "JSON.baseUrlPropertyName")
    private Retrofit retrofitJson;

    @Inject
    @RetrofitXml(captureTrafficLogsPropertyName = "XML.enableTrafficLogging",
            baseUrlPropertyName = "XML.baseUrlPropertyName")
    private Retrofit retrofitXml;

    @Inject
    @RetrofitProtobuf(captureTrafficLogsPropertyName = "ProtoBuf.enableTrafficLogging",
            baseUrlPropertyName = "ProtoBuf.baseUrlPropertyName")
    private Retrofit retrofitProtobuf;

    @Inject
    @RetrofitGson(captureTrafficLogsPropertyName = "ProtoBuf.enableTrafficLogging",
            baseUrlPropertyName = "ProtoBuf.baseUrlPropertyName")
    private Retrofit retrofitGson;

    @Inject
    @RetrofitMoshi(captureTrafficLogsPropertyName = "ProtoBuf.enableTrafficLogging",
            baseUrlPropertyName = "ProtoBuf.baseUrlPropertyName")
    private Retrofit retrofitMoshi;

    @Inject
    @RetrofitJaxB(captureTrafficLogsPropertyName = "XML.enableTrafficLogging",
            baseUrlPropertyName = "XML.baseUrlPropertyName")
    private Retrofit retrofitJaxB;

    @Inject
    @RetrofitJaxB(captureTrafficLogsPropertyName = "XML.enableTrafficLogging",
            baseUrlPropertyName = "XML.baseUrlPropertyName",
            customJAXBContextName = "testCustomJAXBContext")
    private Retrofit customJaxBRetrofit;

    @Inject
    @RetrofitScalar(captureTrafficLogsPropertyName = "Scalars.enableTrafficLogging",
            baseUrlPropertyName = "Scalars.baseUrlPropertyName")
    private Retrofit retrofitScalar;

    @Produces
    @RetrofitProperties
    Properties properties = new Properties();

    public Retrofit2EETest() {
        properties.setProperty("JSON.enableTrafficLogging", "TRUE");
        properties.setProperty("JSON.baseUrlPropertyName", "http://api.zippopotam.us/");
        properties.setProperty("XML.enableTrafficLogging", "TRUE");
        properties.setProperty("XML.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("ProtoBuf.enableTrafficLogging", "TRUE");
        properties.setProperty("ProtoBuf.baseUrlPropertyName", "http://freegeoip.net/");
        properties.setProperty("Scalars.enableTrafficLogging", "TRUE");
        properties.setProperty("Scalars.baseUrlPropertyName", "https://tenki.jp/");
    }

    @Test
    public void testClientNotNull() {
        assertNotNull(retrofitJson);
        assertNotNull(retrofitXml);
        assertNotNull(retrofitProtobuf);
        assertNotNull(retrofitJaxB);
        assertNotNull(customJaxBRetrofit);
    }

    @Test
    public void testZippopotamusClient()
    throws IOException {
        ZippopotamusClient client = retrofitJson.create(ZippopotamusClient.class);
        assertNotNull(client);
        ZippopotamusResponse response = client.getZipInfo("us", "90210").execute().body();
        log.debug("testZippopotamusClient() response:{}", response);
        assertNotNull(response);
        assertEquals("90210", response.getPostCode());
    }

    @Test
    public void testFreeGeoIPClientXml() {
        FreeGeoIPClient client = retrofitXml.create(FreeGeoIPClient.class);
        assertNotNull(client);
    }

    @Test
    public void testFreeGeoIPClientJaxB() {
        FreeGeoIPClient client = retrofitJaxB.create(FreeGeoIPClient.class);
        assertNotNull(client);
    }

    @Test
    public void testFreeGeoIPClientProtobuf() {
        FreeGeoIPClient client = retrofitXml.create(FreeGeoIPClient.class);
        assertNotNull(client);
    }

    @Test
    public void testFreeGeoIPClientGson() {
        FreeGeoIPClient client = retrofitGson.create(FreeGeoIPClient.class);
        assertNotNull(client);
    }

    @Test
    public void testFreeGeoIPClientMoshi() {
        FreeGeoIPClient client = retrofitMoshi.create(FreeGeoIPClient.class);
        assertNotNull(client);
    }

    @Test
    public void testJaxBCustomContext() {
        FreeGeoIPClient client = customJaxBRetrofit.create(FreeGeoIPClient.class);
        assertNotNull(client);
    }

    @Test
    public void testScalar() {
        GoogleClient client = retrofitScalar.create(GoogleClient.class);
        assertNotNull(client);
        try {
            String response = client.getResponse().execute().body();
            //TODO: this is a nifty todo for a different project I'd like to finish one day
//            Document document = Jsoup.parse(response);
//            Elements elements = document.body().getElementsByClass("common-indexes-pickup-wrap");
//            Document docTagName = Jsoup.parse(elements.html());
//            Elements tagName = docTagName.body().getElementsByTag("li");
//            for (int idx = 0; idx < tagName.size(); idx++) {
//                log.debug("testScalar() idx:{}, elements:\n{}", idx, tagName.get(idx));
//                Element element = tagName.get(idx);
//                if (0 < element.getElementsContainingText("洗濯").size()) {
//                    Elements divWithData = element.getElementsByClass("telop");
//                    Element extractData = divWithData.first();
//                    String text = extractData.ownText();
//                    log.debug("testScalar() text:{}", text);
//                    int delimitIndex = text.trim().indexOf("：");
//                    String subst = text.substring(delimitIndex + 1);
//                    log.debug("testScalar() subst:{}", subst);
//                }
//            }
            assertNotNull(response);
        } catch (IOException ioEx) {
            log.error("unexpected error", ioEx);
        }
    }

    @Test
    public void testConstructorsInUtilsWork() {
        assertNotNull(new AdapterUtils());
        assertNotNull(new BeanLookupUtil());
    }
}
