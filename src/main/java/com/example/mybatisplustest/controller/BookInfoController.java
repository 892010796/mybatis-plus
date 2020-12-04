package com.example.mybatisplustest.controller;

import com.example.mybatisplustest.common.ReturnData;
import com.example.mybatisplustest.entity.BookInfo;
import com.example.mybatisplustest.entity.User;
import com.example.mybatisplustest.service.BookInfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/11/26 14:36
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "user", tags = "用户信息(/user)")
public class BookInfoController {

    @Autowired
    private BookInfoService bookInfoService;

    @GetMapping(value = "/get")
    public ReturnData getUser(){
        return bookInfoService.getList();
    }

    @PostMapping(value = "")
    public ReturnData insertUser(){
        return ReturnData.success();
    }

    @DeleteMapping(value = "")
    public ReturnData deleteUser(){
        return ReturnData.success();
    }


}
