package org.geekbang.thinking.in.spring.dependency.lookup;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 通过 {@link org.springframework.beans.factory.ObjectProvider}进行依赖查找
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/13 22:06
 */
public class ObjectProviderDemo {
    // @Configuration 是非必须注解
    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类注册进来（ObjectProviderDemo Class）
        applicationContext.register(ObjectProviderDemo.class);
        // 启动应用上下文
        applicationContext.refresh();
        //    依赖查找集合对象
        lookupByObjectProvider(applicationContext);
        lookupIfAvailable(applicationContext);
        lookupByStreamOps(applicationContext);
        //    关闭上下文
        applicationContext.close();
    }

    private static void lookupByStreamOps(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> userObjectProvider = applicationContext.getBeanProvider(String.class);
        /*Iterable<String> iterable = userObjectProvider;
        for (String str : iterable) {
            System.out.println(str);
        }*/
        // Stream -> Method reference
        userObjectProvider.stream().forEach(System.out::println);
    }

    private static void lookupIfAvailable(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<User> userObjectProvider = applicationContext.getBeanProvider(User.class);
        User user = userObjectProvider.getIfAvailable(User::createUser);
        System.out.println("当前 User 对象:" + user);
    }

    @Bean
    @Primary
    public String helloWorld() {
        // 方法名就是 Bean名称 = "helloWorld"
        return "Hello,World";
    }

    @Bean
    public String message() {
        return "Message";
    }

    private static void lookupByObjectProvider(AnnotationConfigApplicationContext applicationContext) {
        ObjectProvider<String> objectProvider = applicationContext.getBeanProvider(String.class);
        System.out.println("延迟查找:" + objectProvider.getObject());
        Object object = applicationContext.getBean("helloWorld");
        System.out.println("根据name查找:" + object);
    }
}
