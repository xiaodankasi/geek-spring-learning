package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.crypto.interfaces.PBEKey;

/**
 * NoUniqueBeanDefinitionExceptionDemo
 * 演示 {@link NoUniqueBeanDefinitionException}
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 1:28
 */
public class NoUniqueBeanDefinitionExceptionDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类注册进来（NoUniqueBeanDefinitionExceptionDemo Class）
        applicationContext.register(NoUniqueBeanDefinitionExceptionDemo.class);
        // 启动应用上下文
        applicationContext.refresh();

        try {
            // 由于存在多个String 类型的bean ,通过单一类型查找会抛异常
            applicationContext.getBean(String.class);
        } catch (NoUniqueBeanDefinitionException e) {
            System.err
                .printf("Spring 应用上下文存在[%d]个 [%s] 类型的 Bean,具体原因： [%s]\n", e.getNumberOfBeansFound(), String.class.getName(),
                    e.getMessage());
        }
        // 关闭上下文
        applicationContext.close();
    }

    @Bean
    public String bean1() {
        return "1";
    }

    @Bean
    public String bean2() {
        return "2";
    }
    @Bean
    public String bean3() {
        return "3";
    }
}
