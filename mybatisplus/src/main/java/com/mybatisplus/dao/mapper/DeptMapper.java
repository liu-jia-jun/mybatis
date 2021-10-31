package com.mybatisplus.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.Dept;

/**
 * @author 刘佳俊
 * @Date 2021/9/2
 *
 * 这里的DeptMapper是用于AR测试的
 * DeptMapper在AR测试中上是不需要使用，但是MP需要使用DeptMapper获取到数据库中表的信息
 * 如果不定义DeptMapper，MP会报错，找不到表的定义信息
 *
 * 这里的AR是通过实体类自行操作数据库中的表
 *
 */

public interface DeptMapper extends BaseMapper<Dept> {
}
