package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * XmlDependencySetterInjectionDemo
 * 基于 {@link org.springframework.beans.factory.Aware} Aware 回调接口的依赖注入
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 23:57
 */
public class AwareInterfaceDependencyInjectionDemo implements BeanFactoryAware, ApplicationContextAware {

    private static BeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册bean
        context.register(AwareInterfaceDependencyInjectionDemo.class);
        // 启动Spring应用上下文
        context.refresh();
        System.out.println(beanFactory == context.getBeanFactory());
        System.out.println(applicationContext == context);
        // 关闭Spring应用上下文
        context.close();

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AwareInterfaceDependencyInjectionDemo.applicationContext = applicationContext;
    }
}
