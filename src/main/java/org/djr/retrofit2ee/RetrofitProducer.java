package org.djr.retrofit2ee;

import retrofit2.Retrofit;

import javax.enterprise.inject.spi.InjectionPoint;

public interface RetrofitProducer {
	Retrofit getClient(InjectionPoint injectionPoint)
	throws NoSuchFieldException, InstantiationException, IllegalAccessException;
}
