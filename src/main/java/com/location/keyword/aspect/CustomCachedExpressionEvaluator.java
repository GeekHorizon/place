package com.location.keyword.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.expression.AnnotatedElementKey;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.expression.Expression;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomCachedExpressionEvaluator extends CachedExpressionEvaluator {

    private final Map<ExpressionKey, Expression> cache = new ConcurrentHashMap<>(64);

    public<T> T getValue(JoinPoint jp, String expression, Class<T> clz) {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        Method method = methodSignature.getMethod();

        AnnotatedElementKey methodKey = new AnnotatedElementKey(method, clz);
        Method targetMethod = AopUtils.getMostSpecificMethod(method, clz);
        MethodBasedEvaluationContext evaluationContext = new MethodBasedEvaluationContext(jp.getTarget(), targetMethod, jp.getArgs(), getParameterNameDiscoverer());

        return getExpression(cache, new AnnotatedElementKey(method, clz), expression).getValue(evaluationContext, clz);
    }
}
