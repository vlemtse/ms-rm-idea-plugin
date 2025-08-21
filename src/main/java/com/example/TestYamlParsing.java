package com.example;

import com.example.service.YamlParserService;
import com.example.yaml.RoleModel;

public class TestYamlParsing {
    public static void main(String[] args) {
        YamlParserService parserService = new YamlParserService();
        String testFilePath = "test_data/role_models/Core/clients-be.yaml";
        
        System.out.println("Attempting to parse: " + testFilePath);
        RoleModel roleModel = parserService.parseRoleModel(testFilePath);
        
        if (roleModel == null) {
            System.out.println("Failed to parse role model file");
        } else {
            System.out.println("Successfully parsed role model file");
            System.out.println("Permissions count: " + (roleModel.getPermissions() != null ? roleModel.getPermissions().size() : 0));
            System.out.println("Permission groups count: " + (roleModel.getPermissionGroups() != null ? roleModel.getPermissionGroups().size() : 0));
            System.out.println("Roles count: " + (roleModel.getRoles() != null ? roleModel.getRoles().size() : 0));
        }
    }
}