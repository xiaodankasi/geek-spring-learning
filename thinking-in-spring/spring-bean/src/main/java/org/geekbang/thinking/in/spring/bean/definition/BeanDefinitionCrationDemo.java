package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 *
 * @author Administrator
 * @version 1.0
 * @className BeanDefinitionCrationDemo
 * @date 2020/2/5 18:14
 */
public class BeanDefinitionCrationDemo {

    public static void main(String[] args) {

        // 1.通过BeanDefinitoinBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("id", 2);
        beanDefinitionBuilder.addPropertyValue("name", "小王");
        // 获取 beanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        // 2.通过 AbstractBeanDefiniton 以及 派生类 构建

    }
}
