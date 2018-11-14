package com.example.demo.service;

import com.example.demo.model.AyUser;

import java.util.List;

public interface SendJunkMailService {

    boolean sendJunkMail(List<AyUser> ayUserList);

}
