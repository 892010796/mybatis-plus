package com.example.mybatisplustest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplustest.common.ReturnData;
import com.example.mybatisplustest.entity.BookInfo;

public interface BookInfoService extends IService<BookInfo> {

    public ReturnData getList();
}
