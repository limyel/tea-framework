package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.exception.BeanException;
import com.limyel.tea.bean.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeanException;

}
