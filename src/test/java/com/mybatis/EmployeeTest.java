package com.mybatis;

import com.baomidou.mybatisplus.mapper.Condition;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mybatis.entity.Employee;
import com.mybatis.mapper.EmployeeMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeTest {


    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");

    private EmployeeMapper employeeMapper =
            ioc.getBean("employeeMapper", EmployeeMapper.class);

    //-----------------------------------------------------下面是 条件构造器 ---------------------------------------------


    /**
     * 条件构造器 删除操作
     */
    @Test
    public void testEntityWrapperDelete() {
        // 删除名字为Tom 年为29岁

        employeeMapper.delete(
                new EntityWrapper<Employee>()
                    .eq("last_name", "Tom")
                    .eq("age", 29)
        );

    }



    /**
     * 条件构造器 修改操作
     */
    @Test
    public void testEntityWrapperUpdate (){
    // 把名字为Tom 年龄为34 改为

        Employee employee = new Employee();
        employee.setLastName("好老师");
        employee.setEmail("hls@qq.com");
        employee.setGender(0);

        employeeMapper.update(employee,
                new EntityWrapper<Employee>()
                    .eq("last_name", "Tom")
                    .eq("age", 34)
        );
    }



    /**
     * 条件构造器 查询操作
     */
    @Test
    public void testEntityWrapperSelect() {
        // 1.我们需要分页查询tbl_employee表中，年龄在18~50之间且性别为男且姓名为Tom的所有用户
/*        List<Employee> employees = employeeMapper.selectPage(new Page<>(1, 2),  // 分页
                // 这里的字段需要数据库的字段
                new EntityWrapper<Employee>()
                        // 年龄在18~50
                        .between("age", 18, 50)
                        // 性别为男
                        .eq("gender", 1)
                        // 姓名为Tom
                        .eq("last_name", "Tom")
        );*/

        // 使用 Condition 的方式打开如上需求
        List<Employee> employees = employeeMapper.selectPage(
                new Page<>(1, 2),
                Condition.create()
                        .between("age", 18, 50)
                        .eq("gender", 1)
                        .eq("last_name", "Tom")
        );


        // 2.查询表中，性别为女且名字中带有“老师” 或者 邮箱中带有“a”
/*        List<Employee> employees = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .like("last_name", "老师")
                        .or()   // 使用 or() 的sql语句 : gender = ? AND last_name LIKE ? OR email LIKE ?
                        //.orNew()    // orNew() 的sql语句 ：(gender = ? AND last_name LIKE ?) OR (email LIKE ?)
                        .like("email", "a")
        );*/


        // 3.查询性别为女，根据年龄进行排序(asc,desc)，简单分页
/*        List<Employee> employees = employeeMapper.selectList(
                new EntityWrapper<Employee>()
                        .eq("gender", 0)
                        .orderBy("age")
        );*/




        System.out.println(employees);

    }

    //---------------------------------------------------------上面是 条件构造器 -----------------------------------------

    /**
     * 通用 删除操作
     */
    @Test
    public void testCommonDelete() {

        // 1.根据id删除
        Integer id = 6;
        Integer result = employeeMapper.deleteById(id);
        System.out.println("result=" + result);

        // 2. 根据 条件进行删除
        Map<String, Object> map = new HashMap<>();
        map.put("last_name", "MP");
        map.put("email", "mp@qq.com");
        //Integer result = employeeMapper.deleteByMap(map);

        // 3.根据多个id删除
        List<Integer> idList = new ArrayList<>();
        idList.add(5);
        idList.add(6);
        //Integer result = employeeMapper.deleteBatchIds(idList);


    }


    /**
     * 通用 查询操作
     */
    @Test
    public void testCommonSelect() {


        // 1.根据id查询
        Integer id = 7;
        //Employee employee = employeeMapper.selectById(id);


        // 2.根据多个字段查询
        Employee E = new Employee();
        E.setId(7);
        E.setLastName("mp");
        //Employee employee = employeeMapper.selectOne(e);


        // 3.通过多个id查询   <foreach>
        List<Integer> idList = new ArrayList<>();
        idList.add(1);
        idList.add(2);
        idList.add(3);
        /*List<Employee> employees = employeeMapper.selectBatchIds(idList);
        for (Employee employee : employees) {
            System.out.println(employee);
        }*/


        // 4.通过Map封装条件查询
        Map<String, Object> map = new HashMap<>();
        // 注意：这里的名字需要写数据库的字段名。
        map.put("last_name", "MP");
        map.put("gender", 1);
        /*List<Employee> employees = employeeMapper.selectByMap(map);
        for (Employee employee : employees) {
            System.out.println(employee);
        }*/


        //System.out.println(employee);


        // 5.分页查询   (这个分页用的是 内存)（不推荐） 推荐用 pagehelper 或者使用 mybatisPlus自己的分页插件
        Page<Integer> page = new Page<>(2, 2);
        List<Employee> employees = employeeMapper.selectPage(page, null);
        for (Employee employee : employees) {
            System.out.println(employee);
        }


    }


    /**
     * 通用 更新操作
     */
    @Test
    public void testCommonUpdate() {

        Employee employee = new Employee(6, "mybatis", "mybatis@qq.com", 0, 19);

        Integer result = employeeMapper.updateById(employee);

        // 全部更新，如果字段没有值，在数据库库就会更新为 null
        // Integer result = employeeMapper.updateAllColumnById(employee);

        System.out.println("result=" + result);
    }


    /**
     *  通用 插入操作
     * @throws SQLException
     */
    @Test
    public void testCommonInsert() {

        Employee employee = new Employee(null, "mp", "mp@qq.com", 1, 20);

        // 如果字段为空就不会插入
        Integer result = employeeMapper.insert(employee);

        // 全字段插入
        //Integer result = employeeMapper.insertAllColumn(employee);

        System.out.println("result:"+result);

        // 获取当前数据在数据库中主键
        Integer key = employee.getId();
        System.out.println(key);
    }



    @Test
    public void testDataSource() throws SQLException {

        DataSource dataSource = ioc.getBean("dataSource", DataSource.class);

        System.out.println(dataSource);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}
