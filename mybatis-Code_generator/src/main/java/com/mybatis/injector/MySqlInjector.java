package com.mybatis.injector;

import com.baomidou.mybatisplus.entity.TableInfo;
import com.baomidou.mybatisplus.mapper.AutoSqlInjector;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;
import org.springframework.stereotype.Component;

/**
 * 自定义全局操作 :
 * 根据 MybatisPlus 的 AutoSqlInjector 可以自定义各种你想要的 sql ,注入到全局中，相当于自
 * 定义 Mybatisplus 自动注入的方法。
 * 之前需要在 xml 中进行配置的 SQL 语句，现在通过扩展 AutoSqlInjector 在加载 mybatis 环境
 * 时就注入。
 *
 * 1.AutoSqlInjector
 * 1) 在 Mapper 接口中定义相关的 CRUD 方法
 * 2) 扩展 AutoSqlInjector inject 方法，实现 Mapper 接口中方法要注入的 SQL
 * 3) 在 MP 全局策略中，配置 自定义注入器
 */
public class MySqlInjector extends AutoSqlInjector {

    /**
     * 扩展 inject 方法 ，完成自定义全局操作
     */
    @Override
    public void inject(
            Configuration configuration,
            MapperBuilderAssistant builderAssistant,
            Class<?> mapperClass,
            Class<?> modelClass,
            TableInfo table) {

        // 将EmployeeMapper中定义的deleteAll，处理成对应的MapperStatement对象，加入到configuration对象中。

        // 注入的sql语句
        String sql = "delete from " +table.getTableName();
        // 注入的方法名 一定要与EmployeeMapper接口中定义的方法名一致
        String method = "deleteAll";

        // 构造SqlSource对象
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);

        // 构造一个删除的MappedStatement
        this.addDeleteMappedStatement(mapperClass, method, sqlSource);


    }
}
