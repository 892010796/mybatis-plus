package com.example.mybatisplustest.service;

import com.example.mybatisplustest.entity.User;

/**
 * @author 89201
 */
public interface LoginService {
    /**
     * 查询用户信息
     * @param username
     * @return
     */
    User getUserByName(String username);
}
