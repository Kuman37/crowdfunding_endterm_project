package com.crowdfunding.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionUtil {
    public static List<String> inspectClass(Class<?> clazz) {
        List<String> info = new ArrayList<>();
        info.add("Class: " + clazz.getSimpleName());

        info.add("Fields:");
        for (Field f : clazz.getDeclaredFields()) {
            info.add("- " + f.getName() + " (" + f.getType().getSimpleName() + ")");
        }

        info.add("Methods:");
        for (Method m : clazz.getDeclaredMethods()) {
            info.add("- " + m.getName());
        }
        return info;
    }
}