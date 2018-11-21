package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author: weicl
 * @Date: 2018/11/20 6:51 PM
 * @Version 1.0
 * @Description ${description}
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AyUserAttachmentRel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userId;

    private String fileName;

}
