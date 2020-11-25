package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * XmlDependencySetterInjectionDemo
 * 基于 XML 资源的依赖 方法 方法注入
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 23:57
 */
public class AnnotationDependencyMethodInjectionDemo {

    private UserHolder userHolder;

    private UserHolder userHolder2;

    @Autowired
    public void initUserHolder(UserHolder userHolder) {
        this.userHolder = userHolder;
    }

    @Resource
    public void initUserHolder2(UserHolder userHolder) {
        this.userHolder2 = userHolder;
    }

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册bean
        applicationContext.register(AnnotationDependencyMethodInjectionDemo.class);
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(applicationContext);

        String xmlResourcePath = "classpath:/META-INF/dependency-lookup-context.xml";
        // 加载XML资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);
        // 启动Spring应用上下文
        applicationContext.refresh();

        // 依赖查找 AnnotationDependencyFieldInjectionDemo Bean
        AnnotationDependencyMethodInjectionDemo demo =
            applicationContext.getBean(AnnotationDependencyMethodInjectionDemo.class);

        // @Autowire 字段关联
        UserHolder userHolder = demo.userHolder;
        System.out.printf("@Autowire 输出bean [%s]\r\n", demo.userHolder);
        System.out.printf("@Resource 输出bean [%s]\r\n", demo.userHolder2);

        System.out.println(demo.userHolder == demo.userHolder2);
        // 关闭Spring应用上下文
        applicationContext.close();

    }

    @Bean
    public UserHolder userHolder(User user) {
        return new UserHolder(user);
    }
}
