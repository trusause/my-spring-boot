package com.example.demo.dao;

import com.example.demo.model.AyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AyUserDao {

    /**
     *
     * @param name
     * @param password
     * @return
     */
    AyUser findByNameAndPassword(@Param("name") String name, @Param(value = "password") String password);

}
