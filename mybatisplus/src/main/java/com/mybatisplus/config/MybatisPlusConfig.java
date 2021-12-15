package com.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author 刘佳俊
 *
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 注入乐观锁插件
     * 在使用乐观锁时需要先查询再修改这样才能拿到version 和对 version进行修改
     *
     * @return
     */
    @Bean
    public OptimisticLockerInnerInterceptor optimisticLockerInnerInterceptor(){
        return new OptimisticLockerInnerInterceptor();
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor(){
        return new PaginationInnerInterceptor();
    }

    /**
     * SQL 执行性能分析插件
     * 开发环境使用，项目上线之后无需开启
     * maxTime 知道是，sql最大的执行时间
     * 如果sql执行的时间超过maxIime就会抛出异常，但是sql语句可以执行成功
     *
     * 该功能需要引入依赖
     * p6spy
     */

//    @Bean
//    @Profile({"dev","test"})// 设置 dev test 环境开启
//    public PerformanceInterceptor performanceInterceptor() {
//        PerformanceInterceptor performanceInterceptor =  new PerformanceInterceptor();
//        performanceInterceptor.setFormat(true);//格式化语句
//        //performanceInterceptor.setMaxTime(5);//执行时间超过多少秒会抛出异常
//        return  performanceInterceptor;
//    }
}
