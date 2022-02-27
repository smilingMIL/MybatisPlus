package com.mybatis;

import com.mybatis.entity.Employee;
import com.mybatis.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  公共字段自动填充 测试
 *
 *  com.baomidou.mybatisplus.mapper.MetaObjectHandler
 * insertFill(MetaObject metaObject)
 * updateFill(MetaObject metaObject)
 * metaobject: 元对象. 是 Mybatis 提供的一个用于更加方便，更加优雅的访问对象的属性,
 * 给对象的属性设置值 的一个对象. 还会用于包装对象. 支持对 Object 、Map、Collection
 * 等对象进行包装
 * 本质上 metaObject 获取对象的属性值或者是给对象的属性设置值，最终是要
 * 通过 Reflector 获取到属性的对应方法的 Invoker, 最终 invoke.
 *
 * 9.2 开发步骤
 * 1) 注解填充字段 @TableFile(fill = FieldFill.INSERT) 查看 FieldFill
 * 2) 自定义公共字段填充处理器(metaObjectHandler包中)
 * 3) MP 全局注入 自定义公共字段填充处理器
 */
public class FillTest {

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);

    @Test
    public void testMetaObjectHandler() {

        Employee employee = new Employee();
        employee.setLogicFlag(1);

        employeeMapper.insert(employee);

    }

}
