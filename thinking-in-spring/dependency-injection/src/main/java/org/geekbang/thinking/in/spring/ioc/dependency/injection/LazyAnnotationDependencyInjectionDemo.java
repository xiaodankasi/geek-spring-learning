package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * QualifierAnnotationDependencyInjectionDemo
 * {@link org.springframework.beans.factory.ObjectProvider} 延迟注入
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/23 22:39
 */
public class LazyAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    private ObjectProvider<User> userObjectProvider;

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册bean
        applicationContext.register(LazyAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        // 启动Spring应用上下文
        applicationContext.refresh();

        LazyAnnotationDependencyInjectionDemo demo =
            applicationContext.getBean(LazyAnnotationDependencyInjectionDemo.class);
        System.out.println("demo.user: " + demo.user);
        System.out.println("demo.userObjectProvider: " + demo.userObjectProvider.getObject());
        demo.userObjectProvider.forEach(System.out::println);
        // 关闭Spring应用上下文
        applicationContext.close();
    }
}
