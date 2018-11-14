package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:11 PM
 * @Version 1.0
 * @Description ${description}
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AyMood implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 说说内容
     */
    private String content;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 点赞数
     */
    private Integer praiseNum;

    /**
     * 发表时间
     */
    private Date publishTime;

}
