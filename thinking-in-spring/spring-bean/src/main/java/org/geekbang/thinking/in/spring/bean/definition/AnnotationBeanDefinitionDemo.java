package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 注解 的方式注册
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/10 21:50
 */
// 3.通过@Import导入
@Import(AnnotationBeanDefinitionDemo.Config.class)
public class AnnotationBeanDefinitionDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 注册bean
        applicationContext.register(Config.class);
        // 通过BeanDefinition 注册API 实现
        // 1.命名Bean的方式
        registerUserBeanDefinition(applicationContext, "xiaoHe");
        // 2.非命名Bean的方式
        registerUserBeanDefinition(applicationContext);
        // 启动Spring应用上下文
        applicationContext.refresh();
        // 1.通过@bean方式定义
        // 2.通过@Component 方式
        // 3.通过@Import导入
        Map<String, Config> configMap = applicationContext.getBeansOfType(Config.class);
        System.out.println("Config 类型的所有Beans" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User 类型的所有Beans" + applicationContext.getBeansOfType(User.class));
        // 关闭Spring应用上下文
        applicationContext.close();
    }

    // 定义当前类为 Spring Bean
    @Component
    public static class Config {
        //1.通过@bean方式定义
        @Bean(name = {"user", "xiaoMage-user"})
        public User user() {
            User user = new User();
            user.setId(1L);
            user.setName("小风");
            return user;
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry, String beanName) {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 2L).addPropertyValue("name", "老王");

        if (StringUtils.hasText(beanName)) {
            // 通过命名的方式注册BeanDefinition
            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            // 通过 非命名的方式注册 Bean
            BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), registry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry registry) {
        registerUserBeanDefinition(registry, null);
    }
}
