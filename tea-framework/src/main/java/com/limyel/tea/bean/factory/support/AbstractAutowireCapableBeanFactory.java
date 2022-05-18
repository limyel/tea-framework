package com.limyel.tea.bean.factory.support;

import com.limyel.tea.bean.PropertyValue;
import com.limyel.tea.bean.PropertyValues;
import com.limyel.tea.bean.exception.BeanException;
import com.limyel.tea.bean.factory.config.BeanDefinition;
import com.limyel.tea.bean.factory.config.BeanReference;
import com.limyel.tea.util.BeanUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {

    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) {
        Object bean = null;
        try {
            bean = this.createBeanInstance(beanDefinition, beanName, args);
            this.applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeanException("Instantiation of bean failed", e);
        }
        this.addSingleton(beanName, bean);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor<?> constructor = null;
        Class<?> beanClass = beanDefinition.getBeanClass();
        Constructor<?>[] constructors = beanClass.getDeclaredConstructors();
        for (Constructor<?> tmpConstructor: constructors) {
            int argsLength = null == args? 0: args.length;
            // 参数数量是否相同
            if (tmpConstructor.getParameterCount() == argsLength) {
                constructor = tmpConstructor;
                Class<?>[] parameterTypes = tmpConstructor.getParameterTypes();
                // 参数类型是否相同
                for (int i = 0; i < tmpConstructor.getParameterCount(); i++) {
                    if (parameterTypes[i] != args[i].getClass()) {
                        constructor = null;
                        break;
                    }
                }
            }
        }
        return this.getInstantiationStrategy().instantiate(beanDefinition, beanName, constructor, args);
    }

    /**
     * 给 Bean 填充属性
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws BeanException
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) throws BeanException {
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();
            for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                // 引用类
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = this.getBean(beanReference.getBeanName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeanException("Error setting property values：" + beanName);
        }
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
