package com.example.demo.service.impl;

import com.example.demo.model.AyRole;
import com.example.demo.repository.AyRoleRepository;
import com.example.demo.service.AyRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: weicl
 * @Date: 2018/11/21 4:46 PM
 * @Version 1.0
 * @Description ${description}
 */
@Service
public class AyRoleServiceImpl implements AyRoleService {

    @Autowired
    AyRoleRepository ayRoleRepository;

    @Override
    public AyRole find(Integer id) {
        return ayRoleRepository.findById(id).get();
    }
}
