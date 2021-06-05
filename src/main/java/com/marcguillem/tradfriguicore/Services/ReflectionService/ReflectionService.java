package com.marcguillem.tradfriguicore.Services.ReflectionService;


import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReflectionService implements IReflectionService{

    /**
     * Return true if entire object is null
     */
    @Override
    public boolean checkNullFields(Object object) throws Exception {
        Method[] metodos = object.getClass().getDeclaredMethods();
        for(Method metodo: metodos) {
            if(metodo.getName().startsWith("get")) {
                if(metodo.invoke(object) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public List<Method> getSetMethods(Object object) {
        List<Method> listaMetodos;
        List<Method> listaMetodosSet;
        try {
            listaMetodosSet = new ArrayList<>();
            listaMetodos = Arrays.asList(object.getClass().getDeclaredMethods());
            for(Method metodo: listaMetodos) {
                if(metodo.getName().startsWith("set")) {
                    listaMetodosSet.add(metodo);
                }
            }
            return listaMetodosSet;
        } finally {
            listaMetodosSet = null;
            listaMetodos = null;
        }
    }

    @Override
    public List<Method> getGetMethods(Object object) {
        List<Method> listaMetodos;
        List<Method> listaMetodosSet;
        try {
            listaMetodosSet = new ArrayList<>();
            listaMetodos = Arrays.asList(object.getClass().getDeclaredMethods());
            for(Method metodo: listaMetodos) {
                if(metodo.getName().startsWith("get")) {
                    listaMetodosSet.add(metodo);
                }
            }
            return listaMetodosSet;
        } finally {
            listaMetodosSet = null;
            listaMetodos = null;
        }
    }

    @Override
    public List<Field> getFieldsFromClass(Class clase) {
        return Arrays.asList(clase.getDeclaredFields());
    }

    @Override
    public List<Method> getMethods(Object object) {
        List<Method> listaMetodos;
        try {
            listaMetodos = Arrays.asList(object.getClass().getDeclaredMethods());
            return listaMetodos;
        } finally {
            listaMetodos = null;
        }
    }

    @Override
    public Method getMethodFromList(List<Method> metodos, String nombre) {
        for(Method metodo: metodos) {
            if(metodo.getName().equalsIgnoreCase(nombre)) {
                return metodo;
            }
        }
        return null;
    }


}
