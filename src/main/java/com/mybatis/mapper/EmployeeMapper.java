package com.mybatis.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mybatis.entity.Employee;

/**
 * Mapper接口
 *
 * 基于Mybatis：在Mapper接口中编写CRUD相关的方法，提供Mapper接口所对应的SQL映射文件 以及 方法对于的SQL语句。
 *
 * 基于mybatis-plus：让xxMapper接口继承 BaseMapper 接口即可
 *                  BaseMapper<T> :泛型指定就是当前Mapper接口所操作的实体类类型
 *
 */
public interface EmployeeMapper extends BaseMapper<Employee> {


    // Integer insertEmployee(Employee employee);
    // 以前 mybatis 插入完成后想要获取主键需要在mapper.xml里面写。现在mybatisPlus默认自动把主键写入。
    // <insert userGenerateKeys="true" keyProerty="id" > sql..语句 </insert>

}
