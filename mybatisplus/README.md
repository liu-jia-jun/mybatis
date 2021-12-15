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
 
## 数据库事务隔离级别

### 读未提交-read uncommited
> 对方事务还未提交，我们的事务就可以从历史记录中读取到最新的数据
> 产生脏读现象，读取到了未提交（未持久化到硬盘文件上的）的数据

### 读已提交-read commited
> 只用当其他用户的事务已经提交了我们的事务才能读取到最新的数据，解决了脏读现象
> 产生不可重复读现象，即读取的都是最新的数据，无法重复读取到第一次的数据

### 可重复读-repeatable read（MySQL 默认的事务隔离级别）
> 对方事务已经提交之后，我们的事务读取的还是我们事务开启时的数据，解决了不可重复读的问题
> 产生了幻读现象，即读取的数据不是真是的数据

### 序列化读，即串行读取
> 事务执行需要排队，效率低，但是能解决所有的问题

### 乐观锁-用于解决丢失更新问题
> 往表中的字段中添加一个version字段，通过version字段来判断，在自己事务执行的时候，有没有其他事务修改过数据，
> 开始的时候version是1，当我们拿到数据并修改的时候会讲version + 1，提交事务的时候，需要比对version是否是我们读取数据时的version，如果不是，事务执行失败
> 用于解决丢失更新的问题，如果我们提交的数据被其他的事务更改但是我们不知道，称为丢失更新
### 悲观锁
> 悲观的认为，我们每一次修改数据库的操作都会有其他事务来操作我们的数据，从而导致数据的错误，
> 这个时候我们通过给每一次的事务操作加上锁，当我们操作时其他事务无法操作数据，即串行操作数据