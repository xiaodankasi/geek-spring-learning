package org.geekbang.thinking.in.spring.ioc.overview.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * 依赖注入示例
 *
 * @author Administrator
 * @version 1.0
 * @className DependencyInjectionDemo
 * @date 2020/2/1 15:43
 */
public class DependencyInjectionDemo {

    public static void main(String[] args) {
        // 配置 XML 配置文件
        // 启动 Spring 应用上下文
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("META-INF/dependency-injection-context.xml");
        // 1.自定义的bean 依赖来源一
        UserRepository userRepository = beanFactory.getBean("userRepository", UserRepository.class);
        // 2.系统内建依赖，即系统bean的依赖(容器内建依赖不一定是bean),依赖注入的beanFactory 依赖来源二
        System.out.println(userRepository.getBeanFactory());
        // 依赖注入的BeanFactory来源同依赖查找的BeanFactory来源不同 userRepository中的为依赖注入，由spring容器管理
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        // 依赖查找的beanFactory(错误代码) 依赖查找报错
        //System.out.println(beanFactory.getBean(BeanFactory.class));
        // 依赖查找与依赖注入的beanFactory不一致
        ObjectFactory userObjectFactory = userRepository.getObjectFactory();
        System.out.println(userObjectFactory.getObject() == beanFactory);

        // 依赖注入当注入ObjectFactory时，如果注入类型为ApplicationContext,此时注入的bean同beanFactory一样？？原因
        // 3.容器内建bean 依赖来源三
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println("容器内建BEAN:" + environment);
    }
}
