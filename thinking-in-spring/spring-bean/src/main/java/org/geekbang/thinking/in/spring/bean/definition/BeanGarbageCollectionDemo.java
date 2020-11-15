package org.geekbang.thinking.in.spring.bean.definition;

import org.geekbang.thinking.in.spring.bean.factory.UserFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * BeanGarbageCollectionDemo
 * Bean 垃圾回收示例
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/12 23:35
 */
public class BeanGarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationDemo.class);
        // 容器启动
        applicationContext.refresh();
        // 非延迟初始化在 Spring 应用上下文启动完成后被初始化 延迟加载是按需初始化
        System.out.println("Spring 应用上下文已启动...");
        // 容器关闭
        applicationContext.close();
        System.out.println("Spring 应用上下已关闭...");
        Thread.sleep(5000L);
        //  强制处触发GC
        System.gc();
        Thread.sleep(5000L);
    }
}
