package com.example.demo.service;

import com.example.demo.model.AyRole;
import com.example.demo.model.AyUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午4:02
 * @since JDK 1.8
 */
public interface AyRoleService {

    AyRole find(Integer id);

}
