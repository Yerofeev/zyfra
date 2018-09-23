package com.factory.services;

import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Component
public class EntityFields {
    public <T> Map<String, Object> getEntityFields(T entity, List<String> fieldsNotToBeFetched)
            throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        Map<String, Object> map = new LinkedHashMap<>();

        for (Field field : entity.getClass().getDeclaredFields()) {

            if (!fieldsNotToBeFetched.contains(field.getName())){
                map.put(field.getName(), new PropertyDescriptor(field.getName(),
                        entity.getClass()).getReadMethod().invoke(entity));
            }

        }
        return map;
    }
}
