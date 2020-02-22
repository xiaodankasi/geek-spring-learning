package org.geekbang.thinking.in.spring.ioc.overview.dependency.lookup;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BEncoderStream;
import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 依赖查找示例
 * 1.通过名称来查找
 * 2.通过类型来查找
 * 3.通过名称+类型来查找
 * 4.通过注解的方式来查找
 *
 * @author Administrator
 * @version 1.0
 * @className DenpendencyLookupDemo
 * @date 2020/2/1 15:43
 */
public class DenpendencyLookupDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/denpendency-lookup-context.xml");
        //    实时查找bean
        lookupInRealTime(beanFactory);
        //    延时查找bean
        lookupInLazy(beanFactory);
        //    按照类型查找
        lookupInType(beanFactory);
        //    按照类型查找集合
        lookupByCollectionType(beanFactory);
    //    按照名称+类型进行查找
        lookupByNameAndType(beanFactory);
    //    按照注解的方式查找
        lookupByAnnotationType(beanFactory);
    }

    private static void lookupByAnnotationType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ((ListableBeanFactory)beanFactory);
            Map<String, User> users = ((Map)listableBeanFactory.getBeansWithAnnotation(Super.class));
            System.out.println("通过注解查找带@super的bean对象： " + users);
        }
    }

    private static void lookupByNameAndType(BeanFactory beanFactory) {
        User user = beanFactory.getBean("superUser", User.class);
        System.out.println("按照id,type查找的结果：" + user);
    }

    private static void lookupByCollectionType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ((ListableBeanFactory)beanFactory);
            Map<String, User> users = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("按照类型查找的集合对象：" + users);
        }
    }

    private static void lookupInType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("按照类型实时查找单一接口： " + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = ((ObjectFactory)beanFactory.getBean("objectFactory"));
        User user = objectFactory.getObject();
        System.out.println("延时查找bean: " + user);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = ((User)beanFactory.getBean("user"));
        System.out.println("实时查找bean: " + user);
    }
}
