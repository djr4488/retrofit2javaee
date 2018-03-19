package org.djr.retrofit2ee.jaxb;

public class JAXBRetrofitException extends RuntimeException {
    public JAXBRetrofitException() {
        super();
    }

    public JAXBRetrofitException(String message) {
        super(message);
    }

    public JAXBRetrofitException(String message, Throwable cause) {
        super(message, cause);
    }

    public JAXBRetrofitException(Throwable cause) {
        super(cause);
    }

    protected JAXBRetrofitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
