package com.test.xg.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * User: liuweipeng
 * Date: 29/7/2019
 * Description:
 */
public class CheckObjectIsNullUtils {

    /**
     * 判断当前对象中的属性是否都为null
     * @param object
     * @return
     */
    public static boolean objCheckFieldIsNotNull(Object object) {
        if (object == null) {
            return true;
        }
        // 得到类对象
        Class clazz = object.getClass();
        // 得到所有属性
        Field[] fields = clazz.getDeclaredFields();
        //定义返回结果，默认为true
        boolean flag = false;
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            String fieldName = null;
            try {
                //得到属性值
                fieldValue = field.get(object);
                //得到属性类型
                Type fieldType = field.getGenericType();
                //得到属性名
                fieldName = field.getName();
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
            //只要有一个属性值不为null 就返回true
            if ((fieldValue != null && !"".equals(fieldValue))
                    && !"id".equals(fieldName) &&!"name".equals(fieldName) && !"subordinateTeam".equals(fieldName) && !"fixedNumber".equals(fieldName)
            && !"checkingContent".equals(fieldName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}