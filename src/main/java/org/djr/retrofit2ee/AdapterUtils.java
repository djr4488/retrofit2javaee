package org.djr.retrofit2ee;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.adapter.guava.GuavaCallAdapterFactory;
import retrofit2.adapter.java8.Java8CallAdapterFactory;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.adapter.scala.ScalaCallAdapterFactory;

import java.util.concurrent.ExecutorService;

public class AdapterUtils {
    private static final Logger log = LoggerFactory.getLogger(AdapterUtils.class);

    public static void setCallAdapter(AsyncAdapterType asyncAdapterType, SchedulerType schedulerType, Retrofit.Builder retrofitBuilder,
                                        boolean createAsync, ExecutorService executorService) {
        CallAdapter.Factory factory = null;
        switch (asyncAdapterType) {
            case GUAVA:
                factory = getGuavaFactory();
                break;
            case JAVA8:
                factory = getJava8Factory();
                break;
            case RXJAVA: {
                rx.Scheduler scheduler = setRxJava1xScheduler(schedulerType, executorService);
                factory = getRxJavaFactory(createAsync, scheduler);
                break;
            }
            case RXJAVA2: {
                Scheduler scheduler = setScheduler(schedulerType, executorService);
                factory = getRxJava2Factory(createAsync, scheduler);
                break;
            }
            case SCALA: {
                factory = getScalaFactory();
                break;
            }
            case NONE:
                log.trace("setAsyncAdapter() not configured for async");
        }
        if (null != factory) {
            retrofitBuilder.addCallAdapterFactory(factory);
        }
    }

    private static Scheduler setScheduler(SchedulerType schedulerType, ExecutorService executorService) {
        Scheduler scheduler = null;
        switch(schedulerType) {
            case COMPUTATION: {
                scheduler = Schedulers.computation();
                break;
            }
            case IO: {
                scheduler = Schedulers.io();
                break;
            }
            case NEW_THREAD: {
                scheduler = Schedulers.newThread();
                break;
            }
            case RXJAVA2_SINGLE: {
                scheduler = Schedulers.single();
                break;
            }
            case TRAMPOLINE: {
                scheduler = Schedulers.trampoline();
                break;
            }
            case FROM: {
                scheduler = Schedulers.from(executorService);
                break;
            }
            case NONE:
            default: {
                log.trace("setScheduler() no scheduler selected");
            }
        }
        return scheduler;
    }

    private static rx.Scheduler setRxJava1xScheduler(SchedulerType schedulerType, ExecutorService executorService) {
        rx.Scheduler scheduler = null;
        switch(schedulerType) {
            case COMPUTATION: {
                scheduler = rx.schedulers.Schedulers.computation();
                break;
            }
            case IO: {
                scheduler = rx.schedulers.Schedulers.io();
                break;
            }
            case NEW_THREAD: {
                scheduler = rx.schedulers.Schedulers.newThread();
                break;
            }
            case RXJAVA1_IMMEDIATE: {
                scheduler = rx.schedulers.Schedulers.immediate();
                break;
            }
            case TRAMPOLINE: {
                scheduler = rx.schedulers.Schedulers.trampoline();
                break;
            }
            case FROM: {
                scheduler = rx.schedulers.Schedulers.from(executorService);
            }
            case NONE:
            default: {
                log.trace("setScheduler() no scheduler selected");
            }
        }
        return scheduler;
    }

    private static CallAdapter.Factory getRxJava2Factory(boolean createAsync, Scheduler scheduler) {
        CallAdapter.Factory factory;
        if (null != scheduler) {
            factory = RxJava2CallAdapterFactory.createWithScheduler(scheduler);
        } else if (createAsync) {
            factory = RxJava2CallAdapterFactory.createAsync();
        } else {
            factory = RxJava2CallAdapterFactory.create();
        }
        return factory;
    }

    private static CallAdapter.Factory getGuavaFactory() {
        CallAdapter.Factory factory;
        factory = GuavaCallAdapterFactory.create();
        return factory;
    }

    private static CallAdapter.Factory getJava8Factory() {
        CallAdapter.Factory factory;
        factory = Java8CallAdapterFactory.create();
        return factory;
    }

    private static CallAdapter.Factory getRxJavaFactory(boolean createAsync, rx.Scheduler scheduler) {
        CallAdapter.Factory factory;
        if (null != scheduler) {
            factory = RxJavaCallAdapterFactory.createWithScheduler(scheduler);
        } else if (createAsync) {
            factory = RxJavaCallAdapterFactory.createAsync();
        } else {
            factory = RxJavaCallAdapterFactory.create();
        }
        return factory;
    }

    private static CallAdapter.Factory getScalaFactory() {
        CallAdapter.Factory factory;
        factory = ScalaCallAdapterFactory.create();
        return factory;
    }
}
