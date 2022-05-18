package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.exception.BeanException;
import com.limyel.tea.bean.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeanException {
        Class<?> clazz = beanDefinition.getBeanClass();
        try {
            if (null == constructor) {
                return clazz.getDeclaredConstructor( ).newInstance();
            } else {
                return clazz.getDeclaredConstructor(constructor.getParameterTypes()).newInstance(args);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeanException("Failed to instantiate [" + clazz.getName() + "]", e);
        }
    }

}
