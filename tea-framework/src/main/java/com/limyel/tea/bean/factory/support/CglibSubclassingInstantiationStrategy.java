package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.exception.BeanException;
import com.limyel.tea.bean.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeanException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        if (null == constructor) {
            return enhancer.create();
        } else {
            if (constructor.getParameterCount() == 0) {
                args = new Object[0];
            }
            return enhancer.create(constructor.getParameterTypes(), args);
        }
    }

}
