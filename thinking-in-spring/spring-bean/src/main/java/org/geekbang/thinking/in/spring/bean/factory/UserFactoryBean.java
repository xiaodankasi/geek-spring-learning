package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * UserFactoryBean
 * {@link org.geekbang.thinking.in.spring.ioc.overview.domain.User} Bean 的 {@link org.springframework.beans.factory.FactoryBean}
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/10 23:35
 */
public class UserFactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
