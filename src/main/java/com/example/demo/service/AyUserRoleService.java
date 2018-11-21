package com.example.demo.service;

import com.example.demo.model.AyRole;
import com.example.demo.model.AyUserRoleRel;

import java.util.List;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午4:02
 * @since JDK 1.8
 */
public interface AyUserRoleService {

    List<AyUserRoleRel> findByUserId(Integer id);

}
