package org.djr.retrofit2ee.json;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.djr.retrofit2ee.RetrofitProducer;
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
		ObjectMapper objectMapper = null;
		RetrofitJsonConfig jsonClientConfig = injectionPoint.getAnnotated().getAnnotation(RetrofitJsonConfig.class);
		String baseUrlPropertyName = jsonClientConfig.baseUrlPropertyName();
		String captureTrafficLogsPropertyName = jsonClientConfig.captureTrafficLogsPropertyName();
		String baseUrl = System.getProperties().getProperty(baseUrlPropertyName);
		Boolean enableTrafficLogging =
				Boolean.parseBoolean(System.getProperties().getProperty(captureTrafficLogsPropertyName, "FALSE"));
		objectMapper = configureJackson(injectionPoint, objectMapper);
		return getTransport(objectMapper, baseUrl, enableTrafficLogging);
	}

	private ObjectMapper configureJackson(InjectionPoint injectionPoint, ObjectMapper objectMapper)
	throws InstantiationException, IllegalAccessException {
		JacksonModule jacksonModule = injectionPoint.getAnnotated().getAnnotation(JacksonModule.class);
		if (null != jacksonModule) {
			objectMapper = getObjectMapper(objectMapper);
			registerJacksonModulesWithObjectMapper(jacksonModule, objectMapper);
		}
		JacksonMapperFeature jacksonMapperFeature = injectionPoint.getAnnotated().getAnnotation(JacksonMapperFeature.class);
		if (null != jacksonMapperFeature) {
			objectMapper = getObjectMapper(objectMapper);
			configureMapperFeaturesForObjectMapper(jacksonMapperFeature, objectMapper);
		}
		JacksonDeserializationFeature deserializationFeature = injectionPoint.getAnnotated().getAnnotation(JacksonDeserializationFeature.class);
		if (null != deserializationFeature) {
			objectMapper = getObjectMapper(objectMapper);
			configureDeserializationFeaturesForObjectMapper(deserializationFeature, objectMapper);
		}
		JacksonSerializationFeature serializationFeature = injectionPoint.getAnnotated().getAnnotation(JacksonSerializationFeature.class);
		if (null != serializationFeature) {
			objectMapper = getObjectMapper(objectMapper);
			configureDeserializationFeaturesForObjectMapper(serializationFeature, objectMapper);
		}
		return objectMapper;
	}

	private ObjectMapper getObjectMapper(ObjectMapper objectMapper) {
		if (null == objectMapper) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}

	private void registerJacksonModulesWithObjectMapper(JacksonModule jacksonModule, ObjectMapper objectMapper)
	throws InstantiationException, IllegalAccessException {
		for (Class module : jacksonModule.jacksonModules()) {
			objectMapper.registerModule((Module)module.newInstance());
		}
	}

	private void configureMapperFeaturesForObjectMapper(JacksonMapperFeature jacksonMapperFeature, ObjectMapper objectMapper) {
		for (MapperFeatureConfig config : jacksonMapperFeature.features()) {
			objectMapper.configure(config.feature(), config.value());
		}
	}

	private void configureDeserializationFeaturesForObjectMapper(JacksonDeserializationFeature deserializationFeature, ObjectMapper objectMapper) {
		for (DeserializationFeatureConfig config : deserializationFeature.features()) {
			objectMapper.configure(config.feature(), config.value());
		}
	}

	private void configureDeserializationFeaturesForObjectMapper(JacksonSerializationFeature serializationFeature, ObjectMapper objectMapper) {
		for (SerializationFeatureConfig config : serializationFeature.features()) {
			objectMapper.configure(config.feature(), config.value());
		}
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
		return retrofitBuilder.build();
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
