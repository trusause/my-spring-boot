package com.example.demo.rest;

import com.example.demo.dto.ResultDTO;
import com.example.demo.exception.BusinessException;
import com.example.demo.model.AyUser;
import com.example.demo.service.AyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: weicl
 * @Date: 2018/11/20 2:43 PM
 * @Version 1.0
 * @Description ${description}
 */

@Controller
@RequestMapping("/ayUser")
public class AyUserController {

    @Autowired
    AyUserService ayUserService;

    @RequestMapping("/findAll")
    public ResultDTO findAll(Model model) {
        List<AyUser> ayUserList = ayUserService.findAll();
        model.addAttribute("users", ayUserList);
        throw new BusinessException("业务异常！");
    }

    @RequestMapping("/findByNameAndPasswordRetry")
    public ResultDTO findByNameAndPasswordRetry(Model model) {
        AyUser ayUser = ayUserService.findByNameAndPasswordRetry("阿毅", "123456");
        return ResultDTO.builder().build();
    }
}
