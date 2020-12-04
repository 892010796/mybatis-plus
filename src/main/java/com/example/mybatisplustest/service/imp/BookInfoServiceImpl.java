package com.example.mybatisplustest.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplustest.common.ReturnData;
import com.example.mybatisplustest.entity.BookInfo;
import com.example.mybatisplustest.mapper.BookInfoMapper;
import com.example.mybatisplustest.service.BookInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/11/26 14:30
 */
@Service
public class BookInfoServiceImpl extends ServiceImpl<BookInfoMapper, BookInfo> implements BookInfoService {
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Override
    public ReturnData getList(){
        List<BookInfo> bookInfos = bookInfoMapper.selectList(null);
        return ReturnData.success(bookInfos);
    }
}
