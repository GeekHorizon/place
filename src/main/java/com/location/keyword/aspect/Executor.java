package com.location.keyword.aspect;

@FunctionalInterface
public interface Executor<R> {
    public R execution() throws Throwable;
}
