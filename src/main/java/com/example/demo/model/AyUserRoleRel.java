package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author: weicl
 * @Date: 2018/11/13 5:11 PM
 * @Version 1.0
 * @Description 用户-角色关联
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AyUserRoleRel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 用户id
     */
    @Column
    private Integer userId;

    /**
     * 角色id
     */
    @Column
    private Integer roleId;

}
