package com.mybatis;

import com.mybatis.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试自定义全局操作
 * * 自定义全局操作 :
 *  * 根据 MybatisPlus 的 AutoSqlInjector 可以自定义各种你想要的 sql ,注入到全局中，相当于自
 *  * 定义 Mybatisplus 自动注入的方法。
 *  * 之前需要在 xml 中进行配置的 SQL 语句，现在通过扩展 AutoSqlInjector 在加载 mybatis 环境
 *  * 时就注入。
 */
public class CustomTest {


    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);


    @Test
    public void testMySqlInjector() {
        Integer result = employeeMapper.deleteAll();
        System.out.println(result);
    }

}
