package com.mybatis.mapper;

import com.mybatis.entity.Employee;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author smile
 * @since 2022-02-25
 */
public interface EmployeeMapper extends BaseMapper<Employee> {

    int deleteAll();

}
