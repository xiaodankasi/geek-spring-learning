package org.geekbang.thinking.in.spring.ioc.overview.container;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * BeanFactoty IoC 容器示例
 *
 * @author Administrator
 * @version 1.0
 * @className IocContainerDemo
 * @date 2020/2/4 16:57
 */
public class BeanFactotyAsIoCContainerDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        // XML classPath 配置路径
        String location = "META-INF/denpendency-lookup-context.xml";
        //    加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);
        int beanDefinitionCount = reader.loadBeanDefinitions(location);
        System.out.println("bean 定义加载的数量：" + beanDefinitionCount);
        //    依赖查找集合对象
        lookupByCollectionType(listableBeanFactory);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ((ListableBeanFactory)beanFactory);
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("按照类型查找的集合对象：" + users);
        }
    }
}
