package com.example.demo.repository;

import com.example.demo.model.AyMood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:23 PM
 * @Version 1.0
 * @Description ${description}
 */
@Repository
public interface AyMoodRepository extends JpaRepository<AyMood,Integer> {



}
