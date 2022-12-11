package com.location.util;

import java.util.concurrent.*;

public class ExecutorUtil {
    private ThreadPoolExecutor executor;

    public ExecutorUtil() {
        executor = new ThreadPoolExecutor(50, 100, 0L,TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    }

    public <R> Future<R> call(Callable<R> callable) {
        return executor.submit(callable);
    }
}
