package org.geekbang.thinking.in.spring.ioc.overview.domain;

import org.geekbang.thinking.in.spring.ioc.overview.annotation.Super;

/**
 * 超级用户
 *
 * @author Administrator
 * @version 1.0
 * @className SuperUser
 * @date 2020/2/1 16:43
 */
@Super
public class SuperUser extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" + "address='" + address + '\'' + "} " + super.toString();
    }
}
