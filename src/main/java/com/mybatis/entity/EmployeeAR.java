package com.mybatis.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

/**
 * Active Record(活动记录)，是一种领域模型模式，特点是一个模型类对应关系型数据库中的
 * 一个表，而模型类的一个实例对应表中的一行记录。
 *
 * 如何使用 AR 模式: 仅仅需要让实体类继承 Model 类且实现主键指定方法，即可开启 AR 之旅
 */

@TableName("tbl_employee")
public class EmployeeAR extends Model<EmployeeAR> {

    private Integer id ;

    // @TableField(value = "last_name") 如果没有开启驼峰命名，就需要在这指定数据库的字段
    private String lastName;

    private String email ;

    private Integer gender ;

    private Integer age ;

    /**
     * 指定当前实体类的主键属性
     * @return
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
