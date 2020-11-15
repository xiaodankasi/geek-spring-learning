package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.DefaultUserFactory;
import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * BeanInitializationDemo
 * Bean 初始化 Demo
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/11 21:31
 */
@Configuration
public class BeanInitializationDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        // 容器启动
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文启动完成后被初始化 延迟加载是按需初始化
        System.out.println("Spring 应用上下文已启动...");
        // 依赖查找
        UserFactory userFactory = applicationContext.getBean(UserFactory.class);
        System.out.println(userFactory);
        System.out.println("Spring 应用上下文准备关闭...");
        // 容器关闭
        applicationContext.close();
        System.out.println("Spring 应用上下已关闭...");
    }

    @Lazy(value = false)
    @Bean(initMethod = "initUserFactory", destroyMethod = "doDestroy")
    public DefaultUserFactory userFactory() {
        return new DefaultUserFactory();
    }
}
