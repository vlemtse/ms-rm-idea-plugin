package com.example;

import com.example.service.YamlParserService;
import com.example.yaml.RoleDictionary;
import com.example.yaml.RoleModel;
import com.example.yaml.Permission;
import com.example.yaml.PermissionGroup;
import com.example.yaml.Role;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

public class YamlParserServiceTest {

    @Test
    public void testParseRoleModel() {
        YamlParserService parserService = new YamlParserService();
        
        // Получаем путь к тестовому файлу clients-be.yaml
        URL resource = getClass().getClassLoader().getResource("role_models/Core/clients-be.yaml");
        assertNotNull("Test file clients-be.yaml not found", resource);
        
        String filePath = new File(resource.getFile()).getAbsolutePath();
        RoleModel roleModel = parserService.parseRoleModel(filePath);
        
        // Проверяем, что объект не null
        assertNotNull("RoleModel should not be null", roleModel);
        
        // Проверяем permissions
        List<Permission> permissions = roleModel.getPermissions();
        assertNotNull("Permissions should not be null", permissions);
        assertFalse("Should have permissions", permissions.isEmpty());
        
        Permission firstPermission = permissions.get(0);
        assertNotNull("Permission code should not be null", firstPermission.getCode());
        assertNotNull("Permission name should not be null", firstPermission.getName());
        
        // Проверяем permission groups
        List<PermissionGroup> permissionGroups = roleModel.getPermissionGroups();
        assertNotNull("Permission groups should not be null", permissionGroups);
        assertFalse("Should have permission groups", permissionGroups.isEmpty());
        
        PermissionGroup group = permissionGroups.get(0);
        assertNotNull("Group code should not be null", group.getCode());
        assertNotNull("Group name should not be null", group.getName());
        assertNotNull("Permission refs should not be null", group.getPermissionRefs());
        assertFalse("Should have permission refs", group.getPermissionRefs().isEmpty());
        
        // Проверяем роли
        List<Role> roles = roleModel.getRoles();
        assertNotNull("Roles should not be null", roles);
        assertFalse("Should have roles", roles.isEmpty());
        
        Role role = roles.get(0);
        assertNotNull("Role code should not be null", role.getCode());
        assertNotNull("Permission group refs should not be null", role.getPermissionGroupRefs());
        assertNotNull("Permission refs should not be null", role.getPermissionRefs());
    }

    @Test
    public void testParseRoleDictionary() {
        YamlParserService parserService = new YamlParserService();
        
        // Получаем путь к тестовому файлу role_dictionary.yaml
        URL resource = getClass().getClassLoader().getResource("role_models/role_dictionary.yaml");
        assertNotNull("Test file role_dictionary.yaml not found", resource);
        
        String filePath = new File(resource.getFile()).getAbsolutePath();
        RoleDictionary roleDictionary = parserService.parseRoleDictionary(filePath);
        
        // Проверяем, что объект не null
        assertNotNull("RoleDictionary should not be null", roleDictionary);
        
        // Проверяем роли
        List<com.example.yaml.DictionaryRole> roles = roleDictionary.getRoles();
        assertNotNull("Roles should not be null", roles);
        assertFalse("Should have roles", roles.isEmpty());
        
        com.example.yaml.DictionaryRole role = roles.get(0);
        assertNotNull("Role code should not be null", role.getCode());
        assertNotNull("Role name should not be null", role.getName());
        
        // Проверяем sudirRoles
        List<com.example.yaml.SudirRole> sudirRoles = role.getSudirRoles();
        assertNotNull("Sudir roles should not be null", sudirRoles);
        assertFalse("Should have sudir roles", sudirRoles.isEmpty());
        
        com.example.yaml.SudirRole firstSudirRole = sudirRoles.get(0);
        assertNotNull("Sudir role platform code should not be null", firstSudirRole.getPlatformCode());
        assertNotNull("Sudir role code should not be null", firstSudirRole.getRoleCode());
        
        // Проверяем описание
        List<String> descriptions = role.getDescription();
        assertNotNull("Descriptions should not be null", descriptions);
        assertFalse("Should have descriptions", descriptions.isEmpty());
    }
}