package com.example.test;

import com.example.dto.AddressDTO;
import com.example.dto.UserDTO;

/**
 * @Author: weicl
 * @Date: 2018/11/22 4:00 PM
 * @Version 1.0
 * @Description ${description}
 */
public class MainTest {

    public static void main(String[] args) {
        AddressDTO addressDTO = AddressDTO.builder().address("江苏南京").build();
        UserDTO userDTO = UserDTO.builder()
                .name("超级赛亚人")
//                .addressDTO(addressDTO)
                .build();


    }

}
