package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * BeanInstantinationDemo
 * Bean 实例化示例
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/10 23:00
 */
public class BeanInstantiationDemo {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/bean-instantiation-context.xml");
        // 1. 通过静态方法实例化bean
        User user = beanFactory.getBean("user-by-static-method", User.class);
        // 2. 通过实例化方法实例化bean
        User userByInstanceMethod = beanFactory.getBean("user-by-instance-method", User.class);
        // 3.通过FactoryBean实例化bean
        User userByFactoryBean = beanFactory.getBean("user-by-factory-bean", User.class);
        System.out.println("静态方法：" + user);
        System.out.println("实例化方法" + userByInstanceMethod);
        System.out.println("FactoryBean" + userByFactoryBean);
        System.out.println(user == userByFactoryBean);
        System.out.println(user == userByInstanceMethod);

    }
}
