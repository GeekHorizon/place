package com.location.keyword.aspect;

import com.location.keyword.service.KeywordService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionKeywordRankingAspect {
    final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    KeywordService keywordService;

    private CustomCachedExpressionEvaluator customCachedExpressionEvaluator = new CustomCachedExpressionEvaluator();

    @Around("@annotation(com.location.keyword.aspect.ExecutionKeywordRanking)")
    public Object process(ProceedingJoinPoint jp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
        ExecutionKeywordRanking executionPrevent = methodSignature.getMethod().getAnnotation(ExecutionKeywordRanking.class);
        String keyword = customCachedExpressionEvaluator.getValue(jp, executionPrevent.expression(), String.class);

        return ExecuteTemplate.execution(
                jp::proceed,
                idn -> {
                    keywordService.add(idn);
                },
                keyword
        );
    }

}
