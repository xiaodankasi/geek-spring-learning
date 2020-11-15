package org.geekbang.thinking.in.spring.dependency.lookup;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * HierarchicalDependencyLookupDemo
 * 层次性依赖查找示例
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/15 22:00
 */
public class HierarchicalDependencyLookupDemo {

    public static void main(String[] args) {
        // 创建beanFactory容器
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        // 将当前类作为配置类注册进来（HierarchicalDependencyLookupDemo Class）
        applicationContext.register(HierarchicalDependencyLookupDemo.class);

        // 1.获取HierarchicalBeanFactory <- ConfigurableBeanFactory <- ConfigurableListableBeanFactory
        ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        System.out.println("当前 BeanFactory 的Parent BeanFactory :" + beanFactory.getParentBeanFactory());
        // 2.设置 Parent BeanFactory
        ConfigurableListableBeanFactory parentBeanFactory = createParentBeanFactory();
        beanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("当前 BeanFactory 的Parent BeanFactory :" + beanFactory.getParentBeanFactory());
        // 启动应用上下文
        applicationContext.refresh();
        displayContainsLocalBean(beanFactory, "user");
        displayContainsLocalBean(parentBeanFactory, "user");
        displayContainsBean(beanFactory, "user");
        displayContainsBean(parentBeanFactory, "user");
        //    关闭上下文
        applicationContext.close();
    }

    private static void displayContainsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含 LocalBean [name: %s] %s\n", beanFactory, beanName,
            containsBean(beanFactory, beanName));
    }

    private static boolean containsBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory parentHierarchicalBeanFactory =
                HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containsBean(parentHierarchicalBeanFactory, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }

    private static void displayContainsLocalBean(HierarchicalBeanFactory beanFactory, String beanName) {
        System.out.printf("当前BeanFactory[%s]是否包含 LocalBean [name: %s] %s\n", beanFactory, beanName,
            beanFactory.containsLocalBean(beanName));
    }

    private static ConfigurableListableBeanFactory createParentBeanFactory() {

        // 创建beanFactory容器
        DefaultListableBeanFactory listableBeanFactory = new DefaultListableBeanFactory();
        // XML classPath 配置路径
        String location = "classpath:/META-INF/dependency-lookup-context.xml";
        //    加载配置
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(listableBeanFactory);
        reader.loadBeanDefinitions(location);
        return listableBeanFactory;
    }
}
