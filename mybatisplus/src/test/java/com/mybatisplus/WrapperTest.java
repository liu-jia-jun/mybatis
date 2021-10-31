package com.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mybatisplus.dao.mapper.StudentMapper;
import com.mybatisplus.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘佳俊
 * @Date 2021/9/4
 */
@SpringBootTest
public class WrapperTest {

    @Autowired(required = false)
    private StudentMapper studentMapper;


    /**
     * queryWrapper.AllEq(param);
     * <p>
     * 基于 map 内容等于=
     * <p>
     * <p>
     * <p>
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_status = ? AND user_name = ? AND user_age = ?)
     * <p>
     * 使用Map组装查询条件，将map放入到查询构造器QueryWrapper()中 之后调用studentMapper中的方法将查询构造器作为参数传入
     * <p>
     * 这里的查询是将map中的查询条件通过 and 连接起来进行查询
     * <p>
     * 注意这里map组装条件时其中的 key 对应的是数据库中的列名
     */
    @Test
    public void testAllEq() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        Map<String, Object> param = new HashMap<>();
        //注意这里map.put("数据库中的列名",需要查询的值)
        param.put("user_name", "张三");
        param.put("user_age", 22);
        param.put("user_status", 1);
        queryWrapper.allEq(param);
        List<Student> students = studentMapper.selectList(queryWrapper);

        students.forEach(student -> {
            System.out.println(student);
        });
    }

    /**
     * 当 AllEq(Map,boolean) 的第二个参数为false时，如果map参数列表中有 null 值那么该字段不会作为条件进行查询
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name = ?)
     * <p>
     * <p>
     * <p>
     * <p>
     * 当 AllEq(Map,boolean) 的第二个参数为true时，如果map参数列表中有 null 值那么该字段会作为条件进行查询，且值为 null
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name = ? AND user_age IS NULL)
     * <p>
     * <p>
     * 结论：
     * AllEq(map,boolean)
     * true:处理 null 值，where 条件加入 字段  is null 来进行判断
     * false:忽略 null 值，不作为 where 条件
     */
    @Test
    public void testAllEq2() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        Map<String, Object> param = new HashMap<>();
        param.put("user_name", "张三");
        param.put("user_age", null);

        queryWrapper.allEq(param, true);

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> {
            System.out.println(student);
        });
    }


    /**
     * queryMapper.eq("列名",值)：等于
     * <p>
     * 相当于在 where 子句后加上 列名 = 值:等于
     * <p>
     * 多个 eq() 方法使用 and 连接
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name = ? AND user_age = ?)
     */

    @Test
    public void testEq() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", "李四");
        queryWrapper.eq("user_age", 29);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("eq()方法查询：相当于等于：" + student));
    }


    /**
     * queryMapper.ne("列名",值):不等于
     * <p>
     * 相当于在 where 子句后加上 列名 <> 值:不等于
     * <p>
     * 多个 ne() 方法使用 and 连接
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name <> ? AND user_age <> ?)
     */
    @Test
    public void testNe() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("user_name", "张三");
        queryWrapper.ne("user_age", 29);

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("Ne()方法查询：相当于不等于" + student));

    }


    /**
     * queryWrapper.gt("列名",值):大于
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_age > ?)
     * <p>
     * 多个 gt() 方法 where 子句中使用 and 连接
     */
    @Test
    public void testGt() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("user_age", 30);
        queryWrapper.gt("user_status", 0);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("gt()方法查询：" + student));
    }


    /**
     * queryWrapper.ge("列名",值):大于等于 >=
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_age >= ? AND user_status >= ?)
     * <p>
     * 多个 ge() 方法 where 子句中使用 and 连接
     */
    @Test
    public void testGe() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("user_age", 30);
        queryWrapper.ge("user_status", 0);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("ge()方法查询：" + student));
    }

    /**
     * queryWrapper.le("列名",值):小于等于 <=
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_age <= ? AND user_status <= ?)
     * <p>
     * 多个 le() 方法 where 子句中使用 and 连接
     */
    @Test
    public void testLe() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("user_age", 30);
        queryWrapper.le("user_status", 0);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("le()方法查询：" + student));
    }


    /**
     * queryWrapper.lt("列名",值):小于 <
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_age < ? AND user_status < ?)
     * <p>
     * 多个 lt() 方法 where 子句中使用 and 连接
     */
    @Test
    public void testLt() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.lt("user_age", 30);
        queryWrapper.lt("user_status", 0);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("lt()方法查询：" + student));
    }


    /**
     * queryWrapper.between("列名",值1,值2)：between(值1，值2)
     * <p>
     * 值1与值2之间 闭区间
     */
    @Test
    public void testBetween() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("user_age", 0, 100);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("between()方法查询：" + student));
    }


    /**
     * queryWrapper.NotBetween("列名",值1,值2)：notbetween(值1，值2)
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_age NOT BETWEEN ? AND ?)
     * <p>
     * 值1与值2范围之外 闭区间
     */
    @Test
    public void testNotBetween() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.notBetween("user_age", 0, 100);
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("notBetween()方法查询：" + student));
    }


    /**
     * queryWrapper.like("列名",值)
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (email LIKE ?)
     * <p>
     * like 匹配值 %值%  eg: email like %3%
     */
    @Test
    public void testLike() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.like("email", "3");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("like()方法查询：" + student));
    }

    /**
     * queryWrapper.notLike("列名",值)
     * <p>
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (email NOT LIKE ?)
     * <p>
     * like 匹配值 %值%  eg: email not like %3%
     */
    @Test
    public void testNotLike() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.notLike("email", "3");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("notLike()方法查询：" + student));
    }


    /**
     * queryWrapper.likeLeft("列名",值):列名以该值结尾的记录 %值
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name LIKE ?)
     * <p>
     * eg:  %张(String)
     */
    @Test
    public void testLikeLeft() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.likeLeft("user_name", "张");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("likeLeft()方法查询：" + student));
    }

    /**
     * queryWrapper.likeRight("列名",值):列名以该值结尾的记录 值%
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name LIKE ?)
     * <p>
     * eg:  张%(String)
     */
    @Test
    public void testLikeRight() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.likeRight("user_name", "张");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("likeRight()方法查询：" + student));
    }


    /**
     * queryWrapper.isNull("列名",值):查询出该字段中值为 null 的记录
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (email IS NULL)
     * <p>
     * where 列名 is null
     */
    @Test
    public void testIsNull() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("isNull()方法查询：" + student));
    }

    /**
     * queryWrapper.isNull("列名",值):查询出该字段中值不为 null 的记录
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (email IS NOT NULL)
     * <p>
     * <p>
     * where 列名 is not null
     */
    @Test
    public void testIsNotNull() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("email");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("isNull()方法查询：" + student));
    }


    /**
     * queryWrapper.in("列名",值 ...) : 列名 in (值1，值2,...)
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_status IN (?,?))
     * <p>
     * eg:
     * WHERE (user_status IN (?,?))
     * <p>
     * queryWrapper.in("user_status",1,2);
     * queryWrapper.in("user_status",list);
     * 在使用 in() 进行查询时可以添加多个参数也可以使用list集合
     */
    @Test
    public void testIn() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        queryWrapper.in("user_status", list);

//        queryWrapper.in("user_status",1,2);  在使用 in() 进行查询时可以添加多个参数也可以使用list集合

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("in()方法查询：" + student));
    }


    /**
     * queryWrapper.notIn("列名",值 ...) : 列名 not in (值1，值2,...)
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_status NOt IN (?,?))
     * <p>
     * eg:
     * WHERE (user_status NOT IN (?,?))
     * <p>
     * queryWrapper.notIn("user_status",1,2);
     * queryWrapper.notIn("user_status",list);
     * 在使用 in() 进行查询时可以添加多个参数也可以使用list集合
     */
    @Test
    public void testNotIn() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);

        queryWrapper.notIn("user_status", list);

//        queryWrapper.notIn("user_status",1,2);  在使用 notIn() 进行查询时可以添加多个参数也可以使用list集合

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("notIn()方法查询：" + student));
    }


    /**
     * queryWrapper.or():使用 or 进行多个条件的连接
     * <p>
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student WHERE (user_name = ? OR user_age = ?)
     * <p>
     * 此时是将两个eq()方法通过 or 进行连接，默认为 and 连接
     */

    @Test
    public void testOr() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("user_name", "张三")
                .or()
                .eq("user_age", 22);

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("groupBy()方法查询：" + student));
    }


    /**
     * groupBy：分组
     * <p>
     * SELECT user_name,user_age FROM student GROUP BY user_name
     */

    @Test
    public void testGroupBy() {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
//                          填写需要查询的字段
        queryWrapper.select("user_name,user_age")
//                          指定按那个字段进行排序
                .groupBy("user_name");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("groupBy()方法查询：" + student));
    }


    /**
     * queryWrapper.orderByDesc("列名1","列名2",...):依次按照列名顺序进行倒序排列
     *
     * 先按照列名1排序，再依次使用后面的列名进行排序
     *
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student ORDER BY id DESC,user_age DESC
     *
     *
     */

    @Test
    public void testOrderByDesc(){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id","user_age");
        List<Student> students = studentMapper.selectList(queryWrapper);

        students.forEach(student -> System.out.println("groupBy()方法查询：" + student));
    }


    /**
     * queryWrapper.orderByAsc("列名1","列名2",...):依次按照列名顺序进行正序排列
     *
     * 先按照列名1排序，再依次使用后面的列名进行排序
     *
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student ORDER BY id ASC,user_age ASC
     *
     *
     */
    @Test
    public void testOrderByAsc(){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id","user_age");
        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("groupBy()方法查询：" + student));
    }


    /**
     * queryWrapper(boolean condition,boolean isAsc,String columns):更加多个列名进行排序
     *
     *
     * boolean condition : 判断该语句是否加入到sql语句中进行排序
     * boolean isAsc:指定是否为正序排序
     * String columns:需要排序的列名
     *
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student
     * ORDER BY user_name ASC,user_age DESC,email DESC
     *
     */

    @Test
    public void testOrder(){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderBy(true,true,"user_name")
                .orderBy(true,false,"user_age")
                .orderBy(true,false,"email");

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("groupBy()方法查询：" + student));
    }


    /**
     *
     * queryWrapper.select("列名") ：查询该列名的所有记录，后面可以跟上条件
     *
     *
     * queryWrapper.last("需要拼接的 sql 语句"):拼接 sql 语句到Mp的 sql 语句的最后
     *
     */
    @Test
    public void testLast(){

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name").last("limit 3");
        List<Object> students = studentMapper.selectObjs(queryWrapper);
        students.forEach(student -> System.out.println("groupBy()方法查询：" +student ));
    }


    /**
     * inSql 和 notInSql 属于子查询
     *
     * queryWrapper.inSql("列名","子查询语句")：列名 in (select 查询结果)
     *
     *
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student
     * WHERE (user_age IN (select user_age from student where id=1))
     */
    @Test
    public void testInSql(){

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("user_age","select user_age from student where id=1");


        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("inSql()方法查询：" +student ));


    }

    /**
     * inSql 和 notInSql 属于子查询
     *
     * queryWrapper.notInSql("列名","子查询语句")：列名 in (select 查询结果)
     *
     *SELECT id,user_name AS name,user_age AS age,email,user_status FROM student
     * WHERE (user_age NOT IN (select user_age from student where id=1))
     *
     */
    @Test
    public void testNotInSql(){

        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.notInSql("user_age","select user_age from student where id=1");


        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println("notInSql()方法查询：" +student ));


    }

    /**
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student
     * WHERE (EXISTS (select id from student where user_age >90))
     *
     */
    @Test
    public void testExists(){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.exists("select id from student where user_age >90");

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println(student ));

    }


    /**
     * SELECT id,user_name AS name,user_age AS age,email,user_status FROM student
     * WHERE (NOT EXISTS (select id from student where user_age >90))
     *
     */
    @Test
    public void testNotExists(){
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();

        queryWrapper.notExists("select id from student where user_age >90");

        List<Student> students = studentMapper.selectList(queryWrapper);
        students.forEach(student -> System.out.println(student ));

    }



}
