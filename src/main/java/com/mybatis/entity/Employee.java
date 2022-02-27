package com.mybatis.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

/**
 * MybatisPlus会默认使用实体类的类名到数据库中找到对于的表
 *
 * @TableName : 指定数据库的表名。
 */
@TableName("tbl_employee")
public class Employee {

    /**
     * @TableId ：
     *      value : 指定表中的主键列的列名，如果和实体类名与列名一致，可以省略不写
     *      type : 指定主键策略,如：自增。。
     */
    // @TableId(value = "id" , type = IdType.AUTO)   在配置文件启用了全局主键策略
    private Integer id ;

    // @TableField(value = "last_name") 如果没有开启驼峰命名，就需要在这指定数据库的字段
    private String lastName;

    private String email ;

    private Integer gender ;

    private Integer age ;

    //@TableField(exist = false)    // 如果这个字段在数据库不存在，就要加上 @TableField(exist = false)
    //private String thereIsNo;

}
