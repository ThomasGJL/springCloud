package com.example.springbootlog.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {

    //年级
    private String grade;
    //班级
    private String classNumber;
    //姓名
    private String name;
    //年龄
    private int age;
    //地址
    private String address;
    //数学成绩
    private int mathSource;
    //语文成绩
    private int chineseSource;

}
