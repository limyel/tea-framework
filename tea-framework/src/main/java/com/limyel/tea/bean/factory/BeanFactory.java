package com.limyel.tea.bean.factory;

import com.limyel.tea.bean.exception.BeanException;
import com.limyel.tea.bean.factory.config.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface BeanFactory {

    Object getBean(String beanName) throws BeanException;

    /**
     * 带有参数的构造函数
     * @param beanName
     * @param args
     * @return
     * @throws BeanException
     */
    Object getBean(String beanName, Object... args) throws BeanException;

}
