package org.geekbang.thinking.in.spring.ioc.dependency.injection;

import org.geekbang.thinking.in.spring.ioc.overview.domain.User;

/**
 * UserHolder
 * TODO
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/17 0:03
 */
public class UserHolder {

    private User user;

    public UserHolder(User user) {
        this.user = user;
    }

    public UserHolder() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserHolder{" + "user=" + user + '}';
    }
}
