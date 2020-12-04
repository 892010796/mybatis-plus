package com.example.mybatisplustest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * @author 89201
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
    private String id;
    private String userName;
    private String passWord;

    /**
     * 用户对应的角色集合
     */
    private Set<Role> roles;
//    private String roles;
}
