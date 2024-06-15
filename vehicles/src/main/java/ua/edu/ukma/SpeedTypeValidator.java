package ua.edu.ukma;

import java.lang.reflect.Field;
import java.util.Arrays;

public class SpeedTypeValidator {
    public static boolean validateSpeedType(Vehicle obj) throws IllegalAccessException {
        Class<?> objClass = obj.getClass();
        while (objClass != null) {
            for (Field field : objClass.getDeclaredFields()) {
                if (field.isAnnotationPresent(ValidSpeed.class)) {
                    String fieldName = (String) field.get(obj);
                    if (fieldName.equals("km/h") || fieldName.equals("m/s") || fieldName.equals("m/h")) {
                        System.out.println("Speed type is valid!");
                        return true;
                    }
                }
            }
            objClass = objClass.getSuperclass();
        }
        System.err.println("Speed type is invalid!");
        return false;
    }
}
