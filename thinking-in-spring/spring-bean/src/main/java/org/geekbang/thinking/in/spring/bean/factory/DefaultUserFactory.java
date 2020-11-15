package org.geekbang.thinking.in.spring.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * DefaultUserFactory
 * 默认 {@link UserFactory} 实现
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/10 23:24
 */
public class DefaultUserFactory implements UserFactory, InitializingBean, DisposableBean {

    //  基于 @PostConstruct
    @PostConstruct
    public void init() {
        System.out.println("@PostConstruct UserFactory 初始化中...");
    }

    public void initUserFactory() {
        System.out.println("initUserFactory 自定义初始化方法: UserFactory 初始化中 ...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet() : UserFactory 初始化中 ...");

    }

    @PreDestroy
    public void preDestroy() {
        System.out.println("@PreDestroy: UserFactory 销毁中...");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean#Destory : UserFactory 销毁中...");
    }

    public void doDestroy() {
        System.out.println("自定义销毁方法doDestroy : UserFactory 销毁中...");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("当前 DefaultUserFactory 对象正在被垃圾回收...");
    }

}
