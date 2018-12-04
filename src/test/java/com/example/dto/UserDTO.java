package com.example.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: weicl
 * @Date: 2018/11/22 4:01 PM
 * @Version 1.0
 * @Description ${description}
 */
@Data
@Builder
public class UserDTO {

    private String name;

    private AddressDTO addressDTO;

}
