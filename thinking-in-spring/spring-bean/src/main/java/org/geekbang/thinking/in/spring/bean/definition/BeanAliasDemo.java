package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * {@link BeanDefinition}  Bean 别名示例
 *
 * @author Administrator
 * @version 1.0
 * @className BeanDefinitionCrationDemo
 * @date 2020/2/5 18:14
 */
public class BeanAliasDemo {

    public static void main(String[] args) {

        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory =
            new ClassPathXmlApplicationContext("classpath:/META-INF/bean-definitions-context.xml");
        // 通过别名获取曾用名user的bean
        User xiaoMageUser = beanFactory.getBean("xiaoMage-user", User.class);
        User user = beanFactory.getBean("user", User.class);

        System.out.println("xiaoMage-user 是否与 user bean相同: " + "\r\n" + (user == xiaoMageUser));
    }
}
