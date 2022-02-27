package com.mybatis;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.mybatis.entity.EmployeeAR;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class EmployeeARTest {

    private ApplicationContext ioc =
            new ClassPathXmlApplicationContext("applicationContext.xml");



    /**
     * AR   插入操作
     */
    @Test
    public void testARInsert() {
        EmployeeAR employeeAR = new EmployeeAR();
        employeeAR.setLastName("宋老师");
        employeeAR.setEmail("sls@qq.com");
        employeeAR.setGender(1);
        employeeAR.setAge(35);

        boolean result = employeeAR.insert();
        System.out.println(result);
    }


    /**
     * AR   更新操作
     */
    @Test
    public void testARUpdate() {
        EmployeeAR employeeAR = new EmployeeAR();
        employeeAR.setId(12);
        employeeAR.setLastName("宋老shi");
        employeeAR.setEmail("sls@qq.com");
        employeeAR.setGender(1);
        employeeAR.setAge(36);

        boolean result = employeeAR.updateById();
        System.out.println("result=" + result);
    }


    /**
     * AR   查询操作
     */
    @Test
    public void testARSelect() {
        EmployeeAR employeeAR = new EmployeeAR();

        // 1.根据id查询
        //EmployeeAR employee = employeeAR.selectById(12);

        // 2.查询全部
        //List<EmployeeAR> employee = employeeAR.selectAll();

        // 3.根据条件查询
//        List<EmployeeAR> employee = employeeAR.selectList(new EntityWrapper<EmployeeAR>().like("last_name", "老师"));

        // 4.统计查询
        int result = employeeAR.selectCount(new EntityWrapper<EmployeeAR>().eq("gender", 0));

        System.out.println("result=" + result);
        //System.out.println(employee);

    }


    /**
     * AR   删除操作
     */
    @Test
    public void testARDelete() {

        EmployeeAR employeeAR = new EmployeeAR();

        // 1.根据id删除
//        boolean result = employeeAR.deleteById(2);

        // 2.根据条件删除
        boolean result = employeeAR.delete(new EntityWrapper<EmployeeAR>().eq("last_name", "Tom"));

        System.out.println(result);

    }


    /**
     * AR   分页复杂操作
     */
    @Test
    public void testARPage() {

        EmployeeAR employeeAR = new EmployeeAR();

        Page<EmployeeAR> employeeARPage = employeeAR.selectPage(
                new Page<EmployeeAR>(1, 1),
                new EntityWrapper<EmployeeAR>().like("last_name", "老"));

        // 拿到结果
        System.out.println(employeeARPage.getRecords());
    }
}
