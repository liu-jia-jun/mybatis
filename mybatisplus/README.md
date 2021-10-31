# MybatisPlus

## 使用步骤
1. 新建Springboot工程
2. 指定maven中mp的坐标
`  <dependency>
             <groupId>com.baomidou</groupId>
             <artifactId>mybatis-plus-boot-starter</artifactId>
             <version>3.4.2</version>
         </dependency>`
3. 指定MySQL驱动，并配置数据库信息
`<dependency>
             <groupId>mysql</groupId>
             <artifactId>mysql-connector-java</artifactId>
             <scope>runtime</scope>
         </dependency>
         spring:
           datasource:
             driverClassName: com.mysql.cj.jdbc.Driver
             url: jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
             username: root
             password: root
         `
4. 创建实体类 1）定义属性 2）指定主键的类型，即指定主键增长方式和名称
5. 创建Dao层的接口，并继承BaseMapper<实体类>
6. 在Springboot的启动类上加入@MapperScan(value="指定Dao接口的包名")
7.测试使用即可，注入Dao接口，框架实现动态代理创建Dao的实现类对象
    调用BaseMapper中的方法，完成CRUD操作


## ActiveRecord

ActiveRecord：
+ 每个数据库表对应创建一个类，类的每一个对象实例对应于数据库中表的一行记录，通常表的每个字段在类中都有相应的Field
+ ActiveRecord负责把自己持久化，在ActiveRecord中封装了对数据库的访问，通过对象自己实现CRUD，实现数据库的操作
+ ActiveRecord也封装了部分业务逻辑，也可以作为业务对象使用


## 主键增长方式
+ none:即没有主键
+ auto:自动增长（mysql，sql server）
+ input：手动输入
+ ASSIGN_ID:实体类中的主键是Long id 对应的列用bigint，int类型大小不够
+ ASSIGN_UUID:实体类中的主键是String id 列使用 varchar 50
+ 注意：ASSIGN_ID和ASSIGN_UUID:采用的是雪花算法生成一个Long类型或者String的id
        除了none和input主键增长方式，其他增长方式都是自动生成一个id值存储到数据库中



## 查询构造器:Wrapper
查询构造器Wrapper：
QueryWrapper和UpdateWrapper的父类，
用于生成 sql 的 where 条件，entity 属性也用于生成 sql 的 where 条件 MybatisPlus3.0后支持 Lambda表达式
# 延伸阅读
 
