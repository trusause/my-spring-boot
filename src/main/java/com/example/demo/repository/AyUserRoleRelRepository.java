package com.example.demo.repository;

import com.example.demo.model.AyRole;
import com.example.demo.model.AyUserRoleRel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午3:59
 * @since JDK 1.8
 */
@Repository
public interface AyUserRoleRelRepository extends JpaRepository<AyUserRoleRel, Integer>, JpaSpecificationExecutor<AyUserRoleRel> {

    List<AyUserRoleRel> findByUserId(@Param("userId") Integer userId);

}
