package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.exception.BeanException;
import com.limyel.tea.bean.factory.BeanFactory;
import com.limyel.tea.bean.factory.config.BeanDefinition;

import java.util.Optional;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        return this.doGetBean(beanName, null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeanException {
        return this.doGetBean(beanName, args);
    }

    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = this.getSingleton(beanName);
        if (null == bean) {
            BeanDefinition beanDefinition = this.getBeanDefinition(beanName);
            bean = this.createBean(beanName, beanDefinition, args);
        }
        return (T) bean;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeanException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args);
}
