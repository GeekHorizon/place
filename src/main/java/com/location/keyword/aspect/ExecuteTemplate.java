package com.location.keyword.aspect;

import java.util.function.Consumer;

public class ExecuteTemplate {
    public static <R> R execution(Executor<R> execution, Consumer<String> pre, String idn) throws Throwable {
        preExcute(pre, idn);
        return execution.execution();
    }

    private synchronized static void preExcute(Consumer<String> prevent, String idn) {
        prevent.accept(idn);
    }
}
