package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.PostConstruct;

/**
 * BeanCreationExceptionDemo
 * {@link BeanCreationException} 示例
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/16 1:43
 */
public class BeanCreationExceptionDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册BeanDefinition Bean Class 是一个POJO ,不过初始化方法时显性抛出异常
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(POJO.class);

        applicationContext.registerBeanDefinition("errorBean", beanDefinitionBuilder.getBeanDefinition());

        // 启动应用上下文
        applicationContext.refresh();
        //    关闭上下文
        applicationContext.close();
    }

    static class POJO implements InitializingBean {
        @PostConstruct
        public void init() throws Throwable {
            throw new Exception("init() For purpose ...");
        }

        @Override
        public void afterPropertiesSet() throws Exception {
            throw new Exception("afterPropertiesSet() For purpose ...");
        }
    }

}
