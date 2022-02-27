package com.mybatis;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 代码生成器 示例
 */
public class TestMP {

    @Test
    public void testGenerator() {

        // 1.全局配置
        GlobalConfig config = new GlobalConfig();
                // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
              .setAuthor("smile")
                // 生成路径
              .setOutputDir("D:\\XXX\\IDEA\\mybatis-plus\\mybatis-Code_generator\\src\\main\\java")
              // 多次生成时是否文件覆盖
              .setFileOverride(true)
              // 主键策略
              .setIdType(IdType.AUTO)
              // 设置生成的service接口的名字的首字母是否为I
              .setServiceName("%sService")   // %sService就是不生成 I
              //
              .setBaseResultMap(true)
              //
              .setBaseColumnList(true);


        // 2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        // 设置数据库类型
        dataSourceConfig.setDbType(DbType.MYSQL)
                // 数据源信息
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/mybatis-plus")
                .setUsername("root")
                .setPassword("root");


        // 3.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 开启全局大写命名
        strategyConfig.setCapitalMode(true)
                // 指定表名 字段名是否使用下划线
                .setDbColumnUnderline(true)
                // 数据库表映射到实体类的命名策略（驼峰命名）
                .setNaming(NamingStrategy.underline_to_camel)
                // 表名前缀
                .setTablePrefix("tbl_")
                // 生成的表
                .setInclude("tbl_employee");


        // 4.包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        // 指定包生成的路径
        packageConfig.setParent("com.mybatis")
                .setMapper("mapper")
                .setEntity("entity")
                .setController("controller");
        packageConfig.setXml("resources.mapper");

        // 5.整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        // 6.执行
        ag.execute();

    }


}
