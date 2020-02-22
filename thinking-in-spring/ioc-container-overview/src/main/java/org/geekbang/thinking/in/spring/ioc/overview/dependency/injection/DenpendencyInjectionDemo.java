package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;
import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * 依赖注入示例
 *
 * @author Administrator
 * @version 1.0
 * @className DenpendencyInjectionDemo
 * @date 2020/2/1 15:43
 */
public class DenpendencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/denpendency-injection-context.xml");
        // 1.自定义的bean
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        // 2.系统内建依赖,依赖注入的beanFactory
        System.out.println(userRepository.getBeanFactory());
        // 依赖注入的来源同依赖查找的来源不同
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // 依赖查找的beanFactory(错误代码)
        //System.out.println(beanFactory.getBean(BeanFactory.class));
        // 依赖查找与依赖注入的beanFactory不一致
        ObjectFactory userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == beanFactory);

        // 依赖注入当注入ObjectFactory时，如果注入类型为ApplicationContext,此时注入的bean同beanFactory一样？？原因
        // 3.容器内建bean
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("容器内建BEAN:" + environment);
    }
}
