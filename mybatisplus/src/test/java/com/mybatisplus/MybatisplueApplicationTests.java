package com.mybatisplus;

import com.mybatisplus.dao.mapper.UserMapper;
import com.mybatisplus.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class MybatisplueApplicationTests {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Test
    void contextLoads() {
    }


    /**
     * 插入 insert
     */
//    通过mapper.insert()向数据库中插入一条数据
    @Test
    void testInsertUser() {
//        创建 User 对象实例

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setAge(21+i);
            user.setEmail("3142467441@qq.com");
            user.setName("刘佳俊"+i);
            int count = userMapper.insert(user);

            System.out.println(count);

        }


//        该方法将user实例插入到数据库中并返回影响数据库条数(整型)

    }

    //    插入数据之后可以通过插入的对象实例获得该记录中的id值
    @Test
    public void testInsertGetId() {
        //        创建 User 对象实例
        User user = new User();
        user.setAge(21);
        user.setEmail("3142467441@qq.com");
        user.setName("张三");




//        该方法将user实例插入到数据库中并返回影响数据库条数(整型)
        int i = userMapper.insert(user);
//可以直接通过传入insert()方法中的参数获取该记录在数据库中的id值，该功能是通过TableId注解和id的get方法实现的
        System.out.println("该user对象在数据库中的id值" + user.getId());
    }


    /**
     * 更新 update
     */


    /**
     * updateById(user) 该方法传入一个user实例，
     *      根据该实例中的id值来更新user实例中所有的非null的属性值
     *
     *     UPDATE user SET name=?, email=?, age=? WHERE id=?
     *
     * 只要user实例中的属性值不为null就会被更新
     *
     * 注意：基本数据类型有默认值，例如 int 该数据类型的默认值为 0 不为null
     * 即使在user初始化时没有赋值也会默认为0，所以会更新为 0
     * 推荐 user 实体类中的属性不要出现 基本数据类型
     *
     * 实体对象 user：[id = 2,name = "张飞"，email = null , age=0]
     * 此时 email为null不会被修改,age为int型有默认值为0 会被修改
     *
     */
    @Test
    public void testUpdateUser() {
        //        创建 User 对象实例
        User user = new User();
        user.setAge(18);
        user.setEmail("liujiajun@163.com");
        user.setName("鸿星尔克");
        user.setId(2);

        System.out.println("更新操作影响的行数"+userMapper.updateById(user));

    }
    /**
     * 属性值为null的操作实例
     * UPDATE user SET name=? WHERE id=?
     *
     * 当user实例中的属性值为null就不会出现在update语句中
     *
     */
    @Test
    public void testUpdateUser2(){
        //        创建 User 对象实例
        User user = new User();

        user.setName("张飞");
        user.setId(2);

        System.out.println("更新操作影响的行数"+userMapper.updateById(user));

    }


    /**
     * delete 删除
     */

    /**
     * 根据id值来删除记录
     * DELETE FROM user WHERE id=?
     */
    @Test
    public void testDeleteById(){
        System.out.println("删除条数"+userMapper.deleteById(3));
    }

    /**
     * DELETE FROM user WHERE name = ? AND age = ?
     * map.put("字段名"，字段值)
     *
     * 根据传入的map进行更新，传入的条件是 and 的关系
     */

    @Test
    public void testDeleteByMap(){

        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",18);
        int i = userMapper.deleteByMap(map);
        System.out.println("删除记录条数"+i);
    }

    /**
     * DELETE FROM user WHERE id IN ( ? , ? , ? )
     *
     * 批处理方式：通过list集合一次性传入多个主键值，进行删除数据
     * 方法名：deleteBatchIds()
     * 参数：Collection<? extends Serializable>  参数需要的是一个集合并且里面的值是可序列化的
     * 返回值：删除的记录的条数
     */
    @Test
    public void testDeleteBatchIds(){
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        int i = userMapper.deleteBatchIds(ids);
        System.out.println("删除记录条数"+i);

    }


    /**
     * select 查询
     */


    /**
     * 实现查询 selectById，根据主键值查询
     * 参数：主键值
     * 返回值：实体对象
     *
     * SELECT id,name,email,age FROM user WHERE id=?
     *
     * 如果根据主键没有查找到数据，得到的返回值是 null
     */
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(6);

        if(user != null){
            System.out.println("selectById:"+user);
        }
    }

    /**
     * 实现批处理查询，根据多个主键值查询，返回List集合
     * 方法：selectBatchIds(List<T> list)
     * 参数：id的集合
     * 返回值：List<T>
     * SELECT id,name,email,age FROM user WHERE id IN ( ? , ? )
     */
    @Test
    public void testSelectBatchId(){
        List<Integer> ids = new ArrayList<>();
        ids.add(12);
        ids.add(13);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach( u -> {
            System.out.println("查询的user对象："+u);
        });
    }


    /**
     * SELECT id,name,email,age FROM user WHERE name = ? AND age = ?
     *
     * 使用Map做多条件查询，and 连接多个查询条件
     * 方法：selectByMap()
     * 参数：Map<String,Object>
     * 返回值：List<T>
     */
    @Test
    public void testSelectByMap(){
        Map<String,Object> map = new HashMap<>();
//      key是字段名，value：字段值
        map.put("name","刘佳俊2");
        map.put("age",24);

        List<User> users = userMapper.selectByMap(map);
        users.forEach(user -> {
            System.out.println("select"+user);
        });


    }


}

