package com.example.demo.service.impl;

import com.example.demo.model.AyRole;
import com.example.demo.model.AyUserRoleRel;
import com.example.demo.repository.AyRoleRepository;
import com.example.demo.repository.AyUserRoleRelRepository;
import com.example.demo.service.AyRoleService;
import com.example.demo.service.AyUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: weicl
 * @Date: 2018/11/21 4:46 PM
 * @Version 1.0
 * @Description ${description}
 */
@Service
public class AyUserRoleServiceImpl implements AyUserRoleService {

    @Autowired
    AyUserRoleRelRepository ayUserRoleRelRepository;

    @Override
    public List<AyUserRoleRel> findByUserId(Integer id) {
        return ayUserRoleRelRepository.findByUserId(id);
    }
}
