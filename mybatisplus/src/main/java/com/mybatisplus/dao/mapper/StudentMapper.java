package com.mybatisplus.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Wrapper;

/**
 * @author 刘佳俊
 * @Date 2021/9/4
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    Student selectById(Integer id);

    Integer insertStudent(Student student);
}
