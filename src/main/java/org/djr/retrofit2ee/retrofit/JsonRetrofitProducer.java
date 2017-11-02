package org.djr.retrofit2ee.retrofit;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.Retrofit2EEJSON;
import org.djr.retrofit2ee.RetrofitJson;
import org.djr.retrofit2ee.json.JacksonFeature;
import org.djr.retrofit2ee.json.JacksonConfig;
import org.djr.retrofit2ee.json.JacksonModule;
import org.djr.retrofit2ee.json.JacksonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class JsonRetrofitProducer implements RetrofitProducer {
	private static Logger log = LoggerFactory.getLogger(JsonRetrofitProducer.class);

	@Produces
	@RetrofitJson
	public Retrofit getClient(InjectionPoint injectionPoint)
	throws NoSuchFieldException, InstantiationException, IllegalAccessException {
		Retrofit2EEJSON annoConfig = injectionPoint.getAnnotated().getAnnotation(Retrofit2EEJSON.class);
		String baseUrlPropertyName = annoConfig.baseUrlPropertyName();
		String captureTrafficLogsPropertyName = annoConfig.captureTrafficLogsPropertyName();
		String baseUrl = System.getProperties().getProperty(baseUrlPropertyName);
		Boolean enableTrafficLogging =
				Boolean.parseBoolean(System.getProperties().getProperty(captureTrafficLogsPropertyName, "FALSE"));
		ObjectMapper objectMapper = getObjectMapper(annoConfig.jacksonConfiguration());
		return getTransport(objectMapper, baseUrl, enableTrafficLogging);
	}

	private ObjectMapper getObjectMapper(JacksonConfig jacksonConfig)
	throws InstantiationException, IllegalAccessException {
		ObjectMapper objectMapper = null;
		if (null != jacksonConfig) {
			objectMapper = new ObjectMapper();
			JacksonModule[] jacksonModules = jacksonConfig.jacksonModules();
			for (JacksonModule jacksonModule : jacksonModules) {
				objectMapper.registerModule((Module)jacksonModule.jacksonModule().newInstance());
			}
			JacksonProperty jacksonProperties = jacksonConfig.jacksonProperties();
			if (null != jacksonProperties) {
				JacksonFeature[] jacksonFeatures = jacksonProperties.configFeature();
				for (JacksonFeature jacksonFeature : jacksonFeatures) {
					ConfigFeature configFeature = jacksonFeature.feature().getConfigFeature();
					boolean enabled = jacksonFeature.enabled();
					if (configFeature instanceof MapperFeature) {
						objectMapper.configure((MapperFeature) configFeature, enabled);
					} else if (configFeature instanceof DeserializationFeature) {
						objectMapper.configure((DeserializationFeature) configFeature, enabled);
					} else if (configFeature instanceof SerializationFeature) {
						objectMapper.configure((SerializationFeature) configFeature, enabled);
					} else {
						//log that had not feature mapped
					}
				}
			}
		}
		return objectMapper;
	}

	public Retrofit getTransport(ObjectMapper objectMapper, String baseUrl, boolean enableTrafficLogging) {
		log.debug("getTransport() baseUrl:{}, enableTrafficLogging:{}", baseUrl, enableTrafficLogging);
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		setLoggingInterceptor(enableTrafficLogging, httpClient);
		Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
				.baseUrl(baseUrl);
		retrofitBuilder.client(httpClient.build());
		if (null != objectMapper) {
			setConverterFactory(objectMapper, retrofitBuilder);
		}
		Retrofit retrofit = retrofitBuilder.build();
		return retrofit;
	}

	private void setConverterFactory(ObjectMapper objectMapper, Retrofit.Builder retrofitBuilder) {
		if (null != objectMapper) {
			retrofitBuilder.addConverterFactory(JacksonConverterFactory.create(objectMapper))  ;
		}
	}

	private void setLoggingInterceptor(boolean enableTrafficLogging, OkHttpClient.Builder builder) {
		if (enableTrafficLogging) {
			HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
			loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
			builder.addInterceptor(loggingInterceptor);
		}
	}
}
