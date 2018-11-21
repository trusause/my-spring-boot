package com.example.demo.service;

import com.example.demo.exception.BusinessException;
import com.example.demo.model.AyUser;
import com.example.demo.model.AyUserRoleRel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: weicl
 * @Date: 2018/11/21 4:50 PM
 * @Version 1.0
 * @Description ${description}
 */
public class CustomUserService implements UserDetailsService {

    @Autowired
    AyUserService ayUserService;

    @Autowired
    AyUserRoleService ayUserRoleService;

    @Autowired
    AyRoleService ayRoleService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        AyUser ayUser = ayUserService.findByUserName(name);
        if (ayUser == null) {
            throw new BusinessException("用户不存在！");
        }
        //获取用户关联的所有角色
        List<AyUserRoleRel> ayUserRoleRelList = ayUserRoleService.findByUserId(ayUser.getId());
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        if (ayUserRoleRelList != null && ayUserRoleRelList.size() > 0) {
            for (AyUserRoleRel ayUserRoleRel : ayUserRoleRelList) {
                String roleName = ayRoleService.find(ayUserRoleRel.getRoleId()).getName();
                grantedAuthorityList.add(new SimpleGrantedAuthority(roleName));
            }
        }
        return new User(ayUser.getName(), new BCryptPasswordEncoder().encode(ayUser.getPassword()), grantedAuthorityList);
    }
}
