package com.example.demo.repository;

import com.example.demo.model.AyUserAttachmentRel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: weicl
 * @Date: 2018/11/20 6:53 PM
 * @Version 1.0
 * @Description ${description}
 */

@Repository
public interface AyUserAttachmentRelRepository extends MongoRepository<AyUserAttachmentRel,Integer> {

}
