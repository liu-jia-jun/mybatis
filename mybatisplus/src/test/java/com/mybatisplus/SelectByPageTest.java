package com.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.dao.mapper.UserMapper;
import com.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 刘佳俊
 */
@SpringBootTest
public class SelectByPageTest {

    @Autowired
    UserMapper userMapper;
    @Test
    void testPage(){
        // 创建page对象,并传入两个参数，当前第几页和每一页需要显示的记录条数
        Page<User> page = new Page<User>(1,2);

        // 调用mp分页查询的方法，调用分页查询的过程中，底层封装，吧分页的所有数据都封装到page对象中去
        Page<User> userPage = userMapper.selectPage(page, null);

        System.out.println(userPage.getCurrent());//当前页
        System.out.println(userPage.getRecords());//每页数据的list集合
        System.out.println(userPage.getSize());//每页显示记录数
        System.out.println(userPage.getTotal());//总记录数
        System.out.println(userPage.getPages());//总页数

        System.out.println(userPage.hasNext());// 是否有下一页
        System.out.println(userPage.hasPrevious());// 是否有上一页


    }


}
