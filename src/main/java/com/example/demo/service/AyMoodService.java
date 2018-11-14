package com.example.demo.service;

import com.example.demo.model.AyMood;
import org.springframework.stereotype.Service;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:20 PM
 * @Version 1.0
 * @Description ${description}
 */

public interface AyMoodService {

    AyMood save(AyMood ayMood);

    String asynSave(AyMood ayMood);

}
