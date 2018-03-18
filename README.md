# JavaEE Retrofit2
A JavaEE annotation driven Retrofit2 project

Purpose of this was so I didn't have to repeat all the boilerplate code in a JavaEE application in order to simply create a Retrofit2 client.  While not difficult, it was annoying, especially when generally I used all the same things over and over again.  
So I decided I'd write a small annotation driven project which would allow me to use Retrofit2 in an EE application.  I don't know if it would work with SpringFrameworks or not, but in that world I think you have Feign or RestTemplate anyway, so probably no need for this.

# What is supported?
Retrofit 2.4.0(which I do believe is the latest version at this time).  Found here http://square.github.io/retrofit/
Also, JSON(Jackson implementation).  

# What do I hope to support?
All of the standard converters and maybe handlers as well, but we will see if I have time.  Json was good enough for me to get started.

# How to use this thing?
Well you could look at the "Zippopotamus" test and client and see how to use it.  It really is pretty simple.  You configure Jackson using some annotations and you configure Retrofit by stating what the property names are in a retrofit.properties file:
```
    @JacksonModule(jacksonModules = {com.fasterxml.jackson.datatype.jsr310.JavaTimeModule.class})
    @JacksonMapperFeature(features = {
            @MapperFeatureConfig(feature = MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, value = false),
            @MapperFeatureConfig(feature = MapperFeature.AUTO_DETECT_GETTERS, value = true)})
    @JacksonSerializationFeature(features = {
            @SerializationFeatureConfig(feature = SerializationFeature.INDENT_OUTPUT, value = true)})
    @JacksonDeserializationFeature(features = {
            @DeserializationFeatureConfig(feature = DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, value = false)})
    @Inject
    @RetrofitJson(captureTrafficLogsPropertyName = "Retrofit2EETest.enableTrafficLogging",
            baseUrlPropertyName = "Retrofit2EETest.baseUrlPropertyName")
    private Retrofit retrofitJson;
```

Then you have your Retrofit interface as usual:
```
public interface ZippopotamusClient {
    @GET("{country}/{zipcode}")
    Call<ZippopotamusResponse> getZipInfo(@Path("country") String country, @Path("zipcode")String zipcode);
}
```

Then you can just use the client:
```
    @Test
    public void testZippopotamusClient()
            throws IOException {
        ZippopotamusClient client = retrofitJson.create(ZippopotamusClient.class);
        assertNotNull(client);
        ZippopotamusResponse response = client.getZipInfo("us", "90210").execute().body();
        log.debug("testZippopotamusClient() response:{}", response);
    }
```

As for licensing; I use the Apace licensing model.
