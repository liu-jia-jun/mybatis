package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 刘佳俊
 * @Date 2021/9/1
 *
 * 实体类User
 */
@Data
public class User {
    /**
     * 指定主键方式：
     * value：主键字段的名称，如果主键名是id则可以不用写
     * type：指定主键的类型，即主键如何生成
     *          idType.AUTO 表示自动增长
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private Integer age;

}
