package com.example.service;

import com.example.yaml.RoleDictionary;
import com.example.yaml.RoleModel;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.LoaderOptions;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class YamlParserService {

    public RoleModel parseRoleModel(String filePath) {
        try {
            LoaderOptions options = new LoaderOptions();
            CustomClassLoaderConstructor constructor = new CustomClassLoaderConstructor(RoleModel.class, getClass().getClassLoader(), options);
            
            // Настройка PropertyUtils для поддержки kebab-case
            PropertyUtils propertyUtils = new PropertyUtils() {
                @Override
                public Property getProperty(Class<?> type, String name) {
                    // Попытка найти свойство с оригинальным именем
                    try {
                        return super.getProperty(type, name);
                    } catch (Exception e) {
                        // Если не найдено, пытаемся преобразовать kebab-case в camelCase
                        String camelCaseName = toCamelCase(name);
                        try {
                            return super.getProperty(type, camelCaseName);
                        } catch (Exception ex) {
                            // Если и так не найдено, выбрасываем исходное исключение
                            throw e;
                        }
                    }
                }
            };
            constructor.setPropertyUtils(propertyUtils);
            
            Yaml yaml = new Yaml(constructor);
            InputStream inputStream = new FileInputStream(filePath);
            return yaml.load(inputStream);
        } catch (Exception e) {
            System.err.println("Error parsing role model file: " + filePath);
            System.err.println("Error type: " + e.getClass().getName());
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Detailed error:");
            e.printStackTrace();
            return null;
        }
    }

    public RoleDictionary parseRoleDictionary(String filePath) {
        try {
            LoaderOptions options = new LoaderOptions();
            Yaml yaml = new Yaml(new CustomClassLoaderConstructor(RoleDictionary.class, getClass().getClassLoader(), options));
            InputStream inputStream = new FileInputStream(filePath);
            return yaml.load(inputStream);
        } catch (Exception e) {
            System.err.println("Error parsing role dictionary file: " + filePath);
            System.err.println("Error type: " + e.getClass().getName());
            System.err.println("Error message: " + e.getMessage());
            System.err.println("Detailed error:");
            e.printStackTrace();
            return null;
        }
    }
    
    // Метод для преобразования kebab-case в camelCase
    private String toCamelCase(String kebabCase) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;
        
        for (char c : kebabCase.toCharArray()) {
            if (c == '-') {
                capitalizeNext = true;
            } else {
                if (capitalizeNext) {
                    result.append(Character.toUpperCase(c));
                    capitalizeNext = false;
                } else {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
}