package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建示例
 *
 * @author Administrator
 * @version 1.0
 * @className BeanDefinitionCrationDemo
 * @date 2020/2/5 18:14
 */
public class BeanDefinitionCreationDemo {

    public static void main(String[] args) {

        // 只是定义spring bean
        // 1.通过BeanDefinitionBuilder 构建
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        // 通过属性设置
        beanDefinitionBuilder.addPropertyValue("id", 2);
        beanDefinitionBuilder.addPropertyValue("name", "小王");
        // 获取 beanDefinition 实例
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        // 可以自定义修改bean定义
        // 2.通过 AbstractBeanDefinition 以及 派生类 构建
        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        // 设置spring bean的类型
        genericBeanDefinition.setBeanClass(User.class);
        //  通过MutablePropertyValues 批量操作属性
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues.addPropertyValue("id", 3);
        mutablePropertyValues.addPropertyValue("name", "小马哥");
        mutablePropertyValues.add("id", 4).add("name", "超哥");
        //  通过set MutablePropertyValues 批量操作属性
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
    }
}
