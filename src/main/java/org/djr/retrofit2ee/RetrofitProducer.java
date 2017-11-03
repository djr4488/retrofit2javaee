package org.djr.retrofit2ee;

import javax.enterprise.inject.spi.InjectionPoint;

public interface RetrofitProducer {
	public <T> T getClient(InjectionPoint injectionPoint)
	throws NoSuchFieldException, InstantiationException, IllegalAccessException;
}
