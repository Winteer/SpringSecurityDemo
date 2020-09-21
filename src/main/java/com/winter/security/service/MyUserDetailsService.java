package com.winter.security.service;

import com.winter.security.model.User;
import com.winter.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName : MyUserDetailsService
 * @Description :
 * @Author : Winter
 * @Date: 2020-09-21 11:52
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 数据库读出该用户信息
        User user = userRepository.findByUsername(username);
        // 用户不存在，抛出异常
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        // 数据库roles形式解析为UserDetails的权限集
        user.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        return user;
    }


    //自行实现权限转换
    private List<GrantedAuthority> generateAuthorities(String roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String[] roleArray = roles.split(";");
        if (roles != null && !"".equals(roles)) {
            for (String role : roleArray) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
        }
        return authorities;
    }

    protected Authentication createNewAuthentication(Authentication currentAuth, String newPassword) {
        UserDetails user = this.loadUserByUsername(currentAuth.getName());
        UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(user, (Object) null, user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());
        return newAuthentication;
    }

}
