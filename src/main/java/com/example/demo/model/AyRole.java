package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:11 PM
 * @Version 1.0
 * @Description 用户角色实体
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AyRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

}
