package com.mybatis;

import com.baomidou.mybatisplus.plugins.Page;
import com.mybatis.entity.Employee;
import com.mybatis.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 *  分页插件(非内存)
 */
public class PluginTest {


    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    EmployeeMapper employeeMapper = ioc.getBean("employeeMapper", EmployeeMapper.class);


    /**
     *  测试 乐观锁
     */
    @Test
    public void testOptimisticLocker() {

        // 更新操作
        Employee employee = new Employee();
        employee.setId(17);
        employee.setLastName("康ls");
        employee.setVersion(1);

        employeeMapper.updateById(employee);

    }




    /**
     * 测试 性能分析插件
     * com.baomidou.mybatisplus.plugins.PerformanceInterceptor
     * 2) 性能分析拦截器，用于输出每条 SQL 语句及其执行时间
     * 3) SQL 性能执行分析,开发环境使用，超过指定时间，停止运行。有助于发现问题
     */
    @Test
    public void testPerformance() {

        Employee employee = new Employee();
        employee.setLastName("宋ls");
        employee.setEmail("sls@qq.com");
        employee.setGender("0");
        employee.setAge(22);

        Integer result = employeeMapper.insert(employee);
        System.out.println(result);
    }



    /**
     * 测试SQL执行分析插件
     * SQL 执行分析拦截器，只支持 MySQL5.6.3 以上版本
     * 3) 该插件的作用是分析 DELETE UPDATE 语句,防止小白
     * 或者恶意进行 DELETE UPDATE 全表操作
     * 4) 只建议在开发环境中使用，不建议在生产环境使用
     * 5) 在插件的底层 通过 SQL 语句分析命令:Explain 分析当前的 SQL 语句，
     * 根据结果集中的 Extra 列来断定当前是否全表操作。
     */
    @Test
    public void testSQLExplain() {

        // 全表删除
        employeeMapper.delete(null);

    }



    /**
     * 测试分页插件
     */
    @Test
    public void testPage() {

        Page<Employee> page = new Page<>(1, 2);

        List<Employee> employees = employeeMapper.selectPage(page, null);

        System.out.println(employees);

        System.out.println("-----------------获取分页相关的一些信息---------------");

        System.out.println("总条数=" + page.getTotal());
        System.out.println("当前页码=" + page.getCurrent());
        System.out.println("总页码=" + page.getPages());
        System.out.println("每页显示的条数=" + page.getSize());
        System.out.println("是否有上一页=" + page.hasPrevious());
        System.out.println("是否有下一页=" + page.hasNext());


        // 将查询的结果封装到page对象中(方便到view层 显示信息)
        page.setRecords(employees);
        System.out.println("封装后的对象=" + page.getRecords());

    }

}
