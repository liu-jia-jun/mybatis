package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @author 刘佳俊
 * @Date 2021/9/2
 *
 * 如果使用AR(即通过实体类来操作数据库)
 * 就必须继承 Model 类，并指定其中的泛型
 *
 *
 */
@Data
public class Dept extends Model<Dept> {
//    mybatisplus中需要指定数据表和实体类中主键的关系和主键生成方式
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String mobile;
    private Integer manager;
}
