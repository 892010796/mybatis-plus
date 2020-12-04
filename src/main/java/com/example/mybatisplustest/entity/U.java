package com.example.mybatisplustest.entity;

import cn.hutool.core.lang.copier.Copier;

import java.lang.ref.SoftReference;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：zhangjian
 * @date ：Created in 2020/11/26 17:55
 */
class U {
    private static int size;
    //    LinkedList
    // AtomicInteger
// SoftReference
//    public static void main(String[] args) {
//        m1();
//    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        Collections.synchronizedSortedMap()
        for (int i = 0 ; i < 10 ; i++ ) {
            list.add(i + "");
        }
        Iterator<String> iterator = list.iterator();
        int i = 0 ;
        while(iterator.hasNext()) {
            if (i == 3) {
                list.remove(3);
            }
            System.out.println(iterator.next());
            i ++;
        }
    }

    public static void m1() {
        m2();
    }
    public static void m2() {
        m3();
    }
    public static void m3() {
        m4();
    }
    public static void m4() {
        System.out.println("m4执行");
    }
}
