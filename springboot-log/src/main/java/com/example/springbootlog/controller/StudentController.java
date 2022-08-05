package com.example.springbootlog.controller;

import com.alibaba.fastjson.JSON;
import com.example.springbootlog.annotation.SystemLog;
import com.example.springbootlog.entity.Student;
import com.example.springbootlog.util.EncryptUtil;
import com.example.springbootlog.util.PropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/list")
    @SystemLog("学生列表")
    public @ResponseBody
    List<Student> list() {

        ArrayList<Student> list = new ArrayList<Student>();
        Student student1 = new Student("701","1","Angela", 16, "Steven Mill",80,80);
        Student student2 = new Student("702","2","Hannah", 15, "Chan Freeway",85,81);
        Student student3 = new Student("700","3","Renee", 17, "Laurie Ford",82,82);
        Student student4 = new Student("701","2","Jason", 18, "Steven Mill",83,85);
        Student student5 = new Student("702","1","Susan", 19, "Laurie Ford",87,87);
        Student student6 = new Student("703","3","Thomas", 21, "Laurie Ford",88,85);
        Student student7 = new Student("700","1","Steven", 22, "Laurie Ford",90,89);
        Student student8 = new Student("701","3","Michael", 20, "Laurie Ford",81,81);

        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);

        return list;
    }

    public static void main(String[] args) {


        //String properties_2 = PropertiesUtil.getProperties("src/main/resources/config.properties", "concur.api.baseUrl");
        //System.out.println("baseUrl = " + properties_2);
        //System.out.println("*********************************************");

        /**
        EncryptUtil encryptUtil = new EncryptUtil();
        String after64 = encryptUtil.baseEncrypt("tomjerry");
        System.out.println("1 = " + after64.toString());

        String str = encryptUtil.baseDecrypt("dG9tamVycnk=");
        System.out.println("2 = " + str);

        String str1 = encryptUtil.desEncrypt(str,"Concur3@!%");
        String str2 = encryptUtil.desDecrypt(str,str1);

        System.out.println("@ = " + str1);
        System.out.println("# = " + str2);

        System.out.println(System.getenv("path"));
         ***/

        ArrayList<Student> list = new ArrayList<Student>();
        Student student1 = new Student("701","1","Angela", 16, "Steven Mill",80,80);
        Student student2 = new Student("702","2","Hannah", 15, "Chan Freeway",85,81);
        Student student3 = new Student("700","3","Renee", 17, "Laurie Ford",82,82);
        Student student4 = new Student("701","2","Jason", 18, "Steven Mill",83,85);
        Student student5 = new Student("702","1","Susan", 19, "Laurie Ford",87,87);
        Student student6 = new Student("703","3","Thomas", 21, "Laurie Ford",88,85);
        Student student7 = new Student("700","1","Steven", 22, "Laurie Ford",90,89);
        Student student8 = new Student("701","3","Michael", 20, "Laurie Ford",81,81);

        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        list.add(student7);
        list.add(student8);


        List<Student> students = Stream.of(student1,student2,student3,student4,student5,student6,student7,student8).collect(Collectors.toList());

        Map<String, List<String>> collect = students.stream().collect(Collectors.groupingBy(Student::getClassNumber, Collectors.mapping(Student::getName, Collectors.toList())));
        System.out.println(JSON.toJSONString(collect));


    }



}
