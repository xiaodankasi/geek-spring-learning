package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * TypeSafetyDependencyLookupDemo
 * 类型安全 依赖查找示例
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 0:23
 */
public class TypeSafetyDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类注册进来（TypeSafetyDependencyLookupDemo Class）
        applicationContext.register(TypeSafetyDependencyLookupDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        // 演示 BeanFactory#getBean 方法的安全性
        displayBeanFactoryGetBean(applicationContext);
        // 演示 ObjectFactory#getObject 方法的安全性
        displayObjectFactoryGetObject(applicationContext);
        // 演示 ObjectProvider#getIfAvailable 方法的安全性
        displayObjectProviderGetIfAvailable(applicationContext);
        // 演示 ListableBeanFactory#getBeansOfType 方法的安全性
        displayListableBeanFactoryGetBeansOfType(applicationContext);
        // 演示 ObjectProvider#Stream 操作的安全性
        displayObjectProviderStreamOps(applicationContext);
        // DefaultListableBeanFactory 既是单一类型接口，又是集合类型接口，还是层次类型接口，因为
        // DefaultListableBeanFactory(impl) -> ConfigurableListableBeanFactory(extends,层次性) -> ListableBeanFactory(extends,集合型) -> BeanFactory(单一型)
        // 关闭上下文
        applicationContext.close();
    }

    private static void displayObjectProviderStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderStreamOps", () -> userObjectProvider.forEach(System.out::println));
    }

    private static void displayListableBeanFactoryGetBeansOfType(ListableBeanFactory beanFactory) {
        printBeansException("displayListableBeanFactoryGetBeansOfType", () -> beanFactory.getBeansOfType(User.class));
    }

    private static void displayObjectProviderGetIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectProviderGetIfAvailable", userObjectProvider::getIfAvailable);
    }

    private static void displayObjectFactoryGetObject(AnnotationConfigApplicationContext applicationContext) {
        // ObjectProvider is ObjectFactory
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        printBeansException("displayObjectFactoryGetObject", userObjectProvider::getObject);
    }

    private static void printBeansException(String message, Runnable runnable) {
        System.err.println("Source From :" + message);
        System.err
            .println("===========================================================================================");
        try {
            runnable.run();
        } catch (BeansException exception) {
            exception.printStackTrace();
        }
    }

    public static void displayBeanFactoryGetBean(BeanFactory beanFactory) {
        printBeansException("displayBeanFactoryGetBean", () -> beanFactory.getBean(User.class));
    }
}
