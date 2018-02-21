package org.djr.retrofit2ee;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Properties;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by djr4488 on 11/9/17.
 *
 * Copyright 11-9-2017 Danny Rucker

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
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
