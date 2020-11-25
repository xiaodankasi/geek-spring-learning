package org.geekbang.thinking.in.spring.ioc.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * UserGroup
 * 用户组注解，扩展{@link org.springframework.beans.factory.annotation.Qualifier}
 *
 * @author fc
 * @version 1.0
 * @date 2020/11/23 22:53
 */

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}
