package com.winter.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * @ClassName : UserDetails
 * @Description : 用户详情接口
 * @Author : Winter
 * @Date: 2020-09-21 10:20
 */
public interface UserDetails  extends Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
