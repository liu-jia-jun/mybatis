package com.mybatisplus;

import com.mybatisplus.dao.mapper.StudentMapper;
import com.mybatisplus.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 刘佳俊
 * @Date 2021/9/4
 */
@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testSelectById(){
        Student student = studentMapper.selectById(1);
        System.out.println(student);
    }

    /**
     * 通过Mapper.xml中的sql语句进行数据库的操作
     *
     */
    @Test
    public void testInsert(){
        Student student = new Student();

        student.setAge(20);
        student.setEmail("3142467441@qq.com");
        student.setUserStatus(1);
        student.setName("刘佳俊");

        Integer integer = studentMapper.insertStudent(student);
        System.out.println(integer);

    }










}
