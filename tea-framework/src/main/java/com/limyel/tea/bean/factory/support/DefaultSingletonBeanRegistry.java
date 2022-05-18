package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String, Object> singletonObjectMap = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjectMap.get(beanName);
    }

    public void addSingleton(String beanName, Object bean) {
        this.singletonObjectMap.put(beanName, bean);
    }
}
