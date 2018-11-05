package com.ktk.orca.core.exceptions;

public class OrcaApiException extends Exception {

    private ApiError apiError;

    public OrcaApiException() {
        super();
    }

    public OrcaApiException(String message) {
        super(message);
    }

    public OrcaApiException(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }

    public OrcaApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrcaApiException(String message, ApiError apiError, Throwable cause) {
        super(message, cause);
        this.apiError = apiError;
    }

    public OrcaApiException(Throwable cause) {
        super(cause);
    }

    protected OrcaApiException(String message,
                               Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ApiError getApiError(){
        return this.apiError;
    }
}
