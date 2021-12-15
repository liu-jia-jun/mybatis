package com.mybatisplus.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 刘佳俊
 * @Date 2021/9/1
 *
 * 自定义Mapper接口，即dao层
 * 1.继承BaseMapper接口
 * 2.指定BaseMapper接口中的泛型，该泛型是需要操作的实体类
 *
 * BaseMapper 是MP框架中的对象，定义了关于实体类CRUD的相关操作方法
 *
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
