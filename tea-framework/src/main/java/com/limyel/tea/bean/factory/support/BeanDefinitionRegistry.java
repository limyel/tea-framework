package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.factory.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
