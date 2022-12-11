package com.location.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BeanHolder<K, V> implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext;
    private Class<V> classType;
    private Function<V, K> keyMaker;
    private Map<K, V> holder;

    public BeanHolder(Class<V> classType, Function<V, K> keyMaker) {
        this.classType = classType;
        this.keyMaker = keyMaker;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, V> beansOfType = applicationContext.getBeansOfType(classType);

        this.holder = Collections.unmodifiableMap(Optional.ofNullable(beansOfType)
                .orElseGet(Collections::emptyMap)
                .values()
                .stream()
                .collect(Collectors.toMap(keyMaker, Function.identity())));
    }

    public V get(K key) {
        return holder.get(key);
    }

    public List<V> values() {
        return holder.values().stream().collect(Collectors.toList());
    }

    public Set<K> keySet() {
        return holder.keySet();
    }
}
