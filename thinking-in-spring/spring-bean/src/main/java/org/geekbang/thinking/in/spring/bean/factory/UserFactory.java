package org.geekbang.thinking.in.spring.bean.factory;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

/**
 * UserFactory
 * {@link org.geekbang.thinking.in.spring.ioc.overview.domain.User} 工厂类
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/10 23:22
 */
public interface UserFactory {

    default User createUser(){
        return User.createUser();
    }

}
