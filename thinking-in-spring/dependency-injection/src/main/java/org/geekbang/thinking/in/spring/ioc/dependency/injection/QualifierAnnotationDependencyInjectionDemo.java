package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation.UserGroup;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Generated;
import java.util.Collection;

/**
 * QualifierAnnotationDependencyInjectionDemo
 * {@link org.springframework.beans.factory.annotation.Qualifier} 注解依赖注入
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/23 22:39
 */
public class QualifierAnnotationDependencyInjectionDemo {

    @Autowired
    private User user;

    @Autowired
    @Qualifier("user")
    private User namedUser;

    @Autowired
    private Collection<User> allUsers;

    @Autowired
    @Qualifier
    private Collection<User> qualifierUsers;

    @Autowired
    @UserGroup
    private Collection<User> groupUsers;

    @Bean
    @Qualifier   // 进行逻辑分组
    public User user1() {
        return createUser(7L);
    }

    @Bean
    @Qualifier
    public User user2() {
        return createUser(8L);
    }

    @Bean
    @UserGroup
    public User user3() {
        return createUser(9L);
    }

    @Bean
    @UserGroup
    public User user4() {
        return createUser(10L);
    }

    private static User createUser(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册bean
        applicationContext.register(QualifierAnnotationDependencyInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        // 启动Spring应用上下文
        applicationContext.refresh();

        QualifierAnnotationDependencyInjectionDemo demo =
            applicationContext.getBean(QualifierAnnotationDependencyInjectionDemo.class);
        System.out.println("demo.user: " + demo.user);

        System.out.println("demo.namedUser: " + demo.namedUser);

        System.out.println("demo.allUsers: " + demo.allUsers);

        System.out.println("demo.qualifierUsers: " + demo.qualifierUsers);

        System.out.println("demo.groupUsers: " + demo.groupUsers);
        // 关闭Spring应用上下文
        applicationContext.close();
    }
}
