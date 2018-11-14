package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author weicl
 * @version 1.0
 * @date 2018/10/24 下午3:19
 * @since JDK 1.8
 */
@Data
@Table(name = "ay_user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AyUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

}
