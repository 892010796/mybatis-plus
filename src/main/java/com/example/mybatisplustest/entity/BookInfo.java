package com.example.mybatisplustest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/11/26 15:23
 */
@Data
@AllArgsConstructor
public class BookInfo implements Serializable {

    private String bookId;
    private String bookName;
    private String published;

}
