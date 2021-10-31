package javatest01.dao;

import javatest01.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 刘佳俊
 */
public interface UserDao {
    /**
     * 查询结果集
     * @return 返回用户列表
     */
    /**
     * @Transactional( 设置事务传播行为
     * propagation=Propagation.REQUIRED,
     * 设置事务隔离级别
     * insolation=Isolation.DEFAULT,
     * <p>
     * readOnly=false,
     * 设置当程序遇到下列指定异常时一定发生回滚
     * rollbackFor={
     * NullPointerException.class
     * }
     * )
     */

    @Transactional
    List<User> selectUsers();
}
