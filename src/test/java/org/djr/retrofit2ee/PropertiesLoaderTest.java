package org.djr.retrofit2ee;

import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(CdiRunner.class)
@AdditionalClasses({ RetrofitPropertyLoader.class })
public class PropertiesLoaderTest {
    @Inject
    @RetrofitProperties
    private Properties props;

    @Test
    public void testLoadedProperties() {
        assertNotNull(props);
        assertEquals("XML_TEST", props.getProperty("XML.test"));
    }
}
