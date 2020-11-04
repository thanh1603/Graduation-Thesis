/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.khoaluan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vnua.khoaluan.common.Constant;
import vnua.khoaluan.entities.User;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author anjana.m
 */
@Service("mongoUserDetailsService")
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserService iUserService;

    private static final Logger logger = Logger.getLogger(MongoUserDetailsService.class);

    private org.springframework.security.core.userdetails.User userdetails;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        User user = getUserDetail(username);
        userdetails = new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassWord(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                this.getAuthorities(user.getRoles()));
        return userdetails;
    }

    public User getUserDetail(String email) {
        User user = iUserService.findByEmail(email);
        return user;
    }

    private List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        if(roles == null) {
            authList.add(new SimpleGrantedAuthority("ROLE_".concat(Constant.ROLE.USER)));
            return authList;
        }

        for (String role : roles) {
            if (role != null) {
                authList.add(new SimpleGrantedAuthority("ROLE_".concat(role.toUpperCase())));
            }
        }
        return authList;
    }

}