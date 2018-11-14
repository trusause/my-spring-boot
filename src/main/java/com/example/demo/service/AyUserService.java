package com.example.demo.service;

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
public interface AyUserService {

    AyUser findById(Integer id);

    List<AyUser> findAll();

    Future<List<AyUser>> findAsyncAll();

    AyUser save(AyUser ayUser);

    void delete(Integer id);

    Page<AyUser> findAll(Pageable pageable);

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<Integer> ids);

    AyUser findByNameAndPassword(@Param("name") String name, @Param(value = "password") String password);

}
