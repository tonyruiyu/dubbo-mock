package com.tony.test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

@SuppressWarnings("rawtypes") public class ReflectUtil {

    /**
     * Map 中的属性，复值 生成 bean
     * 
     * @param map
     * @param zlass
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T map2Bean(Map map, Class<T> zlass) {

        if (map == null) {
            return null;
        }
        if (map.getClass() == zlass) {//传入的map 对象就是你要的对象
            return (T) map;
        }
        Class[] infs = zlass.getInterfaces();
        for (Class inf : infs) {
            if (inf == Map.class) {//表示你期望对象类型就是一个map
                try {
                    Map r = (Map) zlass.newInstance();
                    r.putAll(map);
                    return (T) r;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            T bean = zlass.newInstance();

            Field[] fields = zlass.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Class fieldType = field.getType();
                Object mapValue = map.get(fieldName);
                if (mapValue != null) {
                    Class mapVClass = mapValue.getClass();
                    if (mapVClass == fieldType) {
                        setProperty(bean, fieldName, mapValue);
                    } else {
                        throw new RuntimeException("不一致的属性类型 ： [fieldName=" + fieldName + ",mapValueType=" + mapVClass.getName() + ",fieldType="
                                + fieldType.getName() + "]");
                    }
                }
            }
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 把一个bean 转化成为 map 。
     * 
     * @param bean
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map bean2Map(Object bean) {
        if (bean == null) {
            return null;
        }
        if (bean instanceof Map) {
            return (Map) bean;
        }
        Map map = new HashMap();
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object fieldValue = getFieldValue(bean, fieldName);
            map.put(fieldName, fieldValue);
        }
        return map;
    }

    /**
     * 对一个对象的一个属性的操作
     * 
     * @param object
     * @param propertyName
     * @param propertyValue
     */
    @SuppressWarnings("unchecked")
    public static void setProperty(Object bean, String fieldName, Object fieldValue) {
        if (bean == null) {
            return;
        }
        if (bean instanceof Map) {
            Map map = (Map) bean;
            map.put(fieldName, fieldValue);
        } else {
            try {
                Field field = null;
                Class zlass = bean.getClass();
                field = getField(zlass, fieldName);//zlass.getDeclaredField(fieldName);
                if (field == null) {
                    throw new RuntimeException("There is no field named as '" + fieldName + "' in Class:" + zlass.getName());
                } else {
                    Method setMethod = fetchSetMethod(zlass, field);
                    if (setMethod != null) {
                        setMethod.invoke(bean, fieldValue);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 获得bean对象 的一个 setMethod
     * 
     * @param zlass
     * @param field
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Method fetchSetMethod(Class zlass, Field field) {
        String fieldName = field.getName();
        String setMethodName = null;
        if (field.getType() == boolean.class) {
            if (fieldName.startsWith("is") && fieldName.length() > 2) {
                char char2 = fieldName.charAt(2);
                if (char2 >= 'A' && char2 <= 'Z') {
                    setMethodName = "set" + fieldName.substring(2);
                }
            }
        }
        if (setMethodName == null) {
            setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }

        try {
            return zlass.getMethod(setMethodName, field.getType());
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 利用反射获取指定对象的指定属性
     * 
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @return 目标属性的值
     */
    @SuppressWarnings("unchecked")
    public static <T> T getFieldValue(Object obj, String fieldName) {
        Object result = null;
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            field.setAccessible(true);
            try {
                result = field.get(obj);
                if (result != null && result instanceof Date) {
                    result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(result);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) result;
    }

    /**
     * 利用反射获取指定对象里面的指定属性
     * 
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @return 目标字段
     */
    private static Field getField(Object obj, String fieldName) {
        Field field = null;
        for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。  
            }
        }
        return field;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * 
     * @param obj
     *            目标对象
     * @param fieldName
     *            目标属性
     * @param fieldValue
     *            目标值
     */
    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) {
        Field field = ReflectUtil.getField(obj, fieldName);
        if (field != null) {
            try {
                field.setAccessible(true);
                field.set(obj, fieldValue);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> void invokeSelectParams(T example, Object param) {
        if (param != null) {
            Class<?> clazz = example.getClass();
            try {
                Method createCriteria = clazz.getDeclaredMethod("createCriteria");
                Object objectCreateCriteria = createCriteria.invoke(example);
                Class<?> clazzCriteria = objectCreateCriteria.getClass();
                Method method;
                Field[] fields = param.getClass().getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    Object obj = field.get(param);
                    String key = field.getName();
                    if (obj == null || StringUtils.isBlank(obj.toString())) {
                        continue;
                    }
                    key = key.substring(0, 1).toUpperCase().concat(key.substring(1));
                    key = "and" + key + "EqualTo";
                    method = clazzCriteria.getDeclaredMethod(key, field.getType());
                    method.invoke(objectCreateCriteria, obj);
                }
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
