package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * XmlDependencySetterInjectionDemo
 * 基于 XML 资源的依赖 Constructor 方法注入
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 23:57
 */
public class XmlDependencyConstructorInjectionDemo {

    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);

        String xmlResourcePath = "classpath:/META-INF/dependency-constructor-injection.xml";
        // 加载XML资源
        beanDefinitionReader.loadBeanDefinitions(xmlResourcePath);

        // 依赖查找并创建bean
        UserHolder userHolder = beanFactory.getBean(UserHolder.class);
        System.out.println(userHolder);

    }
}
