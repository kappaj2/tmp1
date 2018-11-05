package com.ktk.orca.core.exceptions;

public class RestTemplateError extends RuntimeException {

    private ApiError apiError;

    public RestTemplateError() {
        super();
    }

    public RestTemplateError(String message) {
        super(message);
    }

    public RestTemplateError(String message, ApiError apiError) {
        super(message);
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }

    public RestTemplateError(String message, Throwable cause) {
        super(message, cause);
    }

    public RestTemplateError(String message, ApiError apiError, Throwable cause) {
        super(message, cause);
        this.apiError = apiError;
    }

    public RestTemplateError(Throwable cause) {
        super(cause);
    }

    protected RestTemplateError(String message,
                                Throwable cause,
                                boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
