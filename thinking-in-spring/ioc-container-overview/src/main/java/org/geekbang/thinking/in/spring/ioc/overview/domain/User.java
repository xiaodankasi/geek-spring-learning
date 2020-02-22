package org.geekbang.thinking.in.spring.ioc.overview.domain;

/**
 * 用户类
 *
 * @author Administrator
 * @version 1.0
 * @className User
 * @date 2020/2/1 15:53
 */
public class User {

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
