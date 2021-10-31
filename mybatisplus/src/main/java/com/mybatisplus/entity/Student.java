package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 刘佳俊
 * @Date 2021/9/4
 */
@Data
@TableName(value = "student")
public class Student extends Model<Student> {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "user_name")
    private String name;
    @TableField(value = "user_age")
    private Integer age;
    private String email;
    private Integer userStatus;
}
