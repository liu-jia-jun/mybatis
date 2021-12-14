package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

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
    /**
     * 自动填充
     * 在实体类中给属性添加自动填充的注解，可以通过MyMetaObjectHandler类中的方法进行插入和更新
     *
     * MyMetaObjectHandler 需要自己手动实现
     *
     * FieldFill。INSERT     在新增插入时自动填充该字段的值
     *
     * FieldFill.INSERT_UPDATE  在新增插入和更新时会自动填充该字段
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
