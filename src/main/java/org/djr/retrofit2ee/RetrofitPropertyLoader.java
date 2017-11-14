package org.djr.retrofit2ee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by djr4488 on 11/9/17.
 */
@ApplicationScoped
public class RetrofitPropertyLoader {
    @Produces
    @RetrofitProperties
    public Properties loadRetrofitConfigProperties()
    throws IOException {
        Properties prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream("retrofit.properties");
        prop.load(in);
        in.close();
        return prop;
    }
}
