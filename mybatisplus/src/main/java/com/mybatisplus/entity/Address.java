package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

/**
 * @author 刘佳俊
 * @Date 2021/9/4
 *
 * 当数据库中表名，字段名和实体类中的类名和属性名不同时，需要通过注解来指定表名和字段名
 *
 * @TableName(value = "表名")：指定实体类对应在数据库中的表名
 *
 * @TableId(value = "主键名",type = 主键增长方式)：指定主键名在实体类中的映射和主键的增长方式
 *
 * @TableField(value = "字段名")：指定字段名在实体类中的映射，使字段名和属性名对应
 *
 * 当表中字段名为user_street时属性名可以定义成userStreet（驼峰命名方式），此时字段名和属性名可以相映射
 *
 *
 *
 */
@Data
@TableName(value = "user_adderss")
public class Address {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer id;

    @TableField(value = "user_city")
    private String city;

    private String userStreet;


    /**
     * @Version , 是配合乐观锁的使用，数据库表中添加version字段，实体类中添加version属性
     */

    @Version
    @TableField(value = "version",fill = FieldFill.INSERT)
    private Integer version;


}
