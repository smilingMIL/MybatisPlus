package com.mybatis.metaObjectHandler;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 自定义公共字段填充处理器:
 *
 * insertFill(MetaObject metaObject)
 * updateFill(MetaObject metaObject)
 * metaobject: 元对象. 是 Mybatis 提供的一个用于更加方便，更加优雅的访问对象的属性,
 * 给对象的属性设置值 的一个对象. 还会用于包装对象. 支持对 Object 、Map、Collection
 * 等对象进行包装
 * 本质上 metaObject 获取对象的属性值或者是给对象的属性设置值，最终是要
 * 通过 Reflector 获取到属性的对应方法的 Invoker, 最终 invoke.
 *
 */
public class MyMetaObjectHandler extends MetaObjectHandler {

    /**
     * 插入操作 自动填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {

        // 获取到需要被填充的字段的值
        Object fieldValue = getFieldValByName("lastName", metaObject);
        // 判断是否为空
        if (fieldValue == null) {
            System.out.println("*********插入操作 满足填充条件**********");
            setFieldValByName("lastName", "weixin", metaObject);
        }


    }

    /**
     * 修改操作 自动填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {

        // 获取到需要被填充的字段的值
        Object fieldValue = getFieldValByName("lastName", metaObject);
        // 判断是否为空
        if (fieldValue == null) {
            System.out.println("*********修改操作 满足填充条件**********");
            setFieldValByName("lastName", "weixin", metaObject);
        }

    }
}
