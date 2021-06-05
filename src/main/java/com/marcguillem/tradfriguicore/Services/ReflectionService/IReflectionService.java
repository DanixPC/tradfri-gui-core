package com.marcguillem.tradfriguicore.Services.ReflectionService;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface IReflectionService {

    boolean checkNullFields(Object object) throws Exception;

    List<Method> getSetMethods(Object object);

    Method getMethodFromList(List<Method> metodosSet, String nombre);

    List<Method> getMethods(Object object);

    List<Method> getGetMethods(Object object);

    List<Field> getFieldsFromClass(Class clase);

}
