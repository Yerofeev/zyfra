package com.factory.services;

import com.factory.entities.Room;
import com.factory.entities.Sensor;
import com.factory.entities.Tool;
import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class EntityFields {
    public <T> Map<String, Object> getEntityFields(T entity) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new LinkedHashMap<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.getName() != "rooms" && field.getName() != "tools" && field.getName() != "sensors"
                    && field.getName() != "workshop"
                    && field.getName() != "room"
                    && field.getName() != "tool")
            map.put(field.getName(), new PropertyDescriptor(field.getName(), entity.getClass()).getReadMethod().invoke(entity));
        }
        return map;
    }
}
