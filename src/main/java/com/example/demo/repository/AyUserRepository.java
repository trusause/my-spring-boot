package com.example.demo.repository;

import com.example.demo.model.AyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午3:59
 * @since JDK 1.8
 */
@Repository
public interface AyUserRepository extends JpaRepository<AyUser, Integer>, JpaSpecificationExecutor<AyUser> {

//    AyUser findOne(String id);
    

    List<AyUser> findByName(String name);

    List<AyUser> findByNameLike(String name);

    List<AyUser> findByIdIn(Collection<Integer> ids);

}
