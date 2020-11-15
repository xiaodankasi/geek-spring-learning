package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanInstantiationExceptionDemo
 * {@link BeanInstantiationException}
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 1:38
 */
public class BeanInstantiationExceptionDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册BeanDefinition Bean Class 是一个接口
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(CharSequence.class);

        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());
        // 启动应用上下文
        applicationContext.refresh();

        // 关闭上下文
        applicationContext.close();
    }
}
