package com.example.demo.service.impl;

import com.example.demo.model.AyUserAttachmentRel;
import com.example.demo.repository.AyUserAttachmentRelRepository;
import com.example.demo.service.AyUserAttachmentRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: weicl
 * @Date: 2018/11/20 6:54 PM
 * @Version 1.0
 * @Description ${description}
 */

@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {

    @Resource
    AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

//    @Transactional
    @Override
    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel) {
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
