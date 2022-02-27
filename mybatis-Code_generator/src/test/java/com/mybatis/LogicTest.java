package com.mybatis;

import com.mybatis.entity.Employee;
import com.mybatis.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 逻辑删除：
 * 假删除、逻辑删除: 并不会真正的从数据库中将数据删除掉，而是将当前被删除的这条数据
 * 中的一个逻辑删除字段置为删除状态.
 * tbl_user logic_flag = 1 → -1
 * 1) com.baomidou.mybatisplus.mapper.LogicSqlInjector
 * 2) logicDeleteValue 逻辑删除全局值
 * 3) logicNotDeleteValue 逻辑未删除全局值
 * 4) 在 POJO 的逻辑删除字段 添加 @TableLogic 注解
 * 5) 会在 mp 自带查询和更新方法的 sql 后面，追加『逻辑删除字段』=『LogicNotDeleteValue
 * 默认值』 删除方法: deleteById()和其他 delete 方法, 底层 SQL 调用的是 update tbl_xxx
 * set 『逻辑删除字段』=『logicDeleteValue 默认值』
 */
public class LogicTest {

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);


    /**
     * 测试逻辑删除
     */
    @Test
    public void testLogicDelete() {

        Integer result = employeeMapper.deleteById(26);
        System.out.println(result);

//        Employee employee = employeeMapper.selectById(26);
//        System.out.println(employee);

    }

}
