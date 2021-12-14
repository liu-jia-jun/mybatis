package com.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 刘佳俊
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 通过setFieldValByName方法，来给createTime和updateTime字段在插入时自动填充值
     *
     * new Date() ,就是自动填充的值
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
    /**
     * 通过setFieldValByName方法，来给updateTime字段在插入时自动填充值
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
