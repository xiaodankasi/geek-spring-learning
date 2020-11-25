package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * 注解驱动的依赖注入处理过程
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/23 22:39
 */
public class AnnotationDependencyInjectionResolutionDemo {

    // DependencyDescriptor -> 必须(required = true)
    // 实时注入(eager = true) +
    // 通过类型(User.class)依赖查找 (处理) +
    // 字段名称("user")
    // primary = true

    @Autowired
    private User user;

    // user superUser
    // 集合类型

    @Autowired
    private Map<String, User> users;

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册bean
        applicationContext.register(AnnotationDependencyInjectionResolutionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        // 启动Spring应用上下文
        applicationContext.refresh();

        AnnotationDependencyInjectionResolutionDemo demo =
            applicationContext.getBean(AnnotationDependencyInjectionResolutionDemo.class);
        System.out.println("demo.user: " + demo.user);
        // 关闭Spring应用上下文
        applicationContext.close();
    }
}
