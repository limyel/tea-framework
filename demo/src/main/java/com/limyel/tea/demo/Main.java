package com.limyel.tea.demo;

import com.limyel.tea.bean.factory.config.BeanDefinition;
import com.limyel.tea.bean.factory.BeanFactory;
import com.limyel.tea.bean.factory.support.DefaultListableBeanFactory;
import com.limyel.tea.demo.entity.Person;

public class Main {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        beanFactory.registerBeanDefinition("person", beanDefinition);
        System.out.println(beanFactory.getBean("person"));
    }
}
