package com.mybatisplus;

import com.mybatisplus.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 刘佳俊
 * @Date 2021/9/2
 */


@SpringBootTest
public class DeptARTest {


    /**
     * AR操作可以自己封装了对数据库的操作
     *
     * 使用AR必须在对应的实体类上继承 Model<T>
     */


    /**
     * INSERT INTO dept ( name, mobile, manager ) VALUES ( ?, ?, ? )
     * object.insert()：调用实体对象自己的方法，完成对象自身持久化到数据库的添加操作
     * 就直接将object插入到数据库中
     */
    @Test
    public void testARInsert() {
        Dept dept = new Dept();
        dept.setManager(1);
        dept.setMobile("15279436128");
        dept.setName("程序员");
        boolean insert = dept.insert();

        System.out.println("插入是否成功：" + insert);
    }

    /**
     * object.update() 根据object中的id值来进行更新操作
     * <p>
     * UPDATE dept SET mobile=?, manager=? WHERE id=?
     * <p>
     * 根据id来找到具体记录
     * 根据object中的非空内容来更新记录，如果该属性值为空就不会更新
     */

    @Test
    public void testARUpdate() {
        Dept dept = new Dept();
        dept.setId(2L);
        dept.setManager(10);
        dept.setMobile("15279436128");

        boolean insert = dept.updateById();

        System.out.println("更新是否成功：" + insert);
    }


    /**
     * DELETE FROM dept WHERE id=?
     *
     * object.deleteById([id]) 删除操作
     * 有参：根据传入的id值来进行删除操作
     * 无参：如果无参那么object的id值不能为空，否则会报错
     *
     * 成功删除返回true，失败返回false
     *
     */
    @Test
    public void testARDeleteById() {
        Dept dept = new Dept();
        boolean b = dept.deleteById(1);
        Dept dept1 = new Dept();
        dept1.setId(3L);
        boolean b1 = dept1.deleteById();

        System.out.println("dept删除是否成功：" + b);
        System.out.println("dept1删除是否成功：" + b1);
    }


    /**
     * SELECT id,name,mobile,manager FROM dept WHERE id=?
     *
     * object.selectById([id]) 查询操作
     * 有参：根据传入的id值来进行查询操作
     * 无参：如果无参那么object的id值不能为空，否则会报错
     *
     * 成功删除返回唯一一条记录，失败返回null
     *
     */
    @Test
    public void testARSelectById(){
        //无参
        Dept dept = new Dept();
        dept.setId(1L);
        Dept dept1 = dept.selectById();

        System.out.println("部门："+dept1);

        //有参
        Dept dept2 = new Dept();

        Dept dept3 = dept.selectById(3);

        System.out.println("部门："+dept3);

    }




}
