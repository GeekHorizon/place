package com.location.place.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class RestResponse<T> {
    private boolean success;
    private T result;
    private HttpStatus httpStatus;
    private final LocalDateTime responseTime;
    private Exception exception;

    private RestResponse(
            boolean success,
            T result,
            HttpStatus status) {
        this.success = success;
        this.result = result;
        this.responseTime = LocalDateTime.now();
    }

    private RestResponse(
            boolean success,
            Exception exception,
            HttpStatus status) {
        this.success = success;
        this.exception = exception;
        this.httpStatus = status;
        this.responseTime = LocalDateTime.now();
    }

    public static <R> RestResponse<R> of(R response, HttpStatus status) {
        return new RestResponse<>(true, response, status);
    }

    public static <R> RestResponse<R> of(Exception exception, HttpStatus status) {
        return new RestResponse<>(false, exception, status);
    }

    public boolean isSuccess() {
        return success;
    }

    public T getResult() {
        return result;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public LocalDateTime getResponseTime() {
        return responseTime;
    }

    public Exception getException() {
        return exception;
    }
}
