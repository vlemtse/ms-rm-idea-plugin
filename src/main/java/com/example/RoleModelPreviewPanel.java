package com.example;

import com.example.service.YamlParserService;
import com.example.yaml.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBScrollPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.*;

public class RoleModelPreviewPanel extends JPanel {
    private final Project project;
    private final VirtualFile file;
    private final JTextArea textArea;

    public RoleModelPreviewPanel(Project project, VirtualFile file) {
        this.project = project;
        this.file = file;
        this.textArea = new JTextArea();
        this.textArea.setEditable(false);
        
        setLayout(new BorderLayout());
        JBScrollPane scrollPane = new JBScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);
        
        refresh();
    }

    public void refresh() {
        try {
            // Parse the role model file
            YamlParserService parserService = new YamlParserService();
            String roleModelPath = file.getPath();
            RoleModel roleModel = parserService.parseRoleModel(roleModelPath);
            
            if (roleModel == null) {
                textArea.setText("Error: Could not parse role model file: " + roleModelPath + "\n\n" +
                               "Possible reasons:\n" +
                               "1. The YAML file format is incorrect\n" +
                               "2. The file doesn't exist or is not accessible\n" +
                               "3. The file structure doesn't match the expected schema\n" +
                               "4. There might be a syntax error in the YAML\n\n" +
                               "Check the IDE logs for more detailed error information.");
                return;
            }
            
            // Find the role dictionary file
            File roleModelFile = new File(roleModelPath);
            File parentDir = roleModelFile.getParentFile();
            File roleDictionaryFile = new File(parentDir, "role_dictionary.yaml");
            
            // If not found in the same directory, try the parent directory
            if (!roleDictionaryFile.exists()) {
                roleDictionaryFile = new File(parentDir, "../role_dictionary.yaml");
            }
            
            // If still not found, try looking for example_role_dictionary.yaml
            if (!roleDictionaryFile.exists()) {
                roleDictionaryFile = new File(parentDir, "example_role_dictionary.yaml");
            }
            
            // If still not found, try looking for example_role_dictionary.yaml in parent directory
            if (!roleDictionaryFile.exists()) {
                roleDictionaryFile = new File(parentDir, "../example_role_dictionary.yaml");
            }
            
            String roleDictionaryPath = roleDictionaryFile.getCanonicalPath();
            
            RoleDictionary roleDictionary = parserService.parseRoleDictionary(roleDictionaryPath);
            
            if (roleDictionary == null) {
                textArea.setText("Error: Could not parse role dictionary file: " + roleDictionaryPath);
                return;
            }
            
            // Generate the preview
            StringBuilder preview = new StringBuilder();
            
            // Create maps for easier lookup
            Map<String, Permission> permissionMap = new HashMap<>();
            if (roleModel.getPermissions() != null) {
                for (Permission permission : roleModel.getPermissions()) {
                    permissionMap.put(permission.getCode(), permission);
                }
            }
            
            Map<String, PermissionGroup> permissionGroupMap = new HashMap<>();
            if (roleModel.getPermissionGroups() != null) {
                for (PermissionGroup group : roleModel.getPermissionGroups()) {
                    permissionGroupMap.put(group.getCode(), group);
                }
            }
            
            Map<String, DictionaryRole> dictionaryRoleMap = new HashMap<>();
            if (roleDictionary.getRoles() != null) {
                for (DictionaryRole dictRole : roleDictionary.getRoles()) {
                    dictionaryRoleMap.put(dictRole.getCode(), dictRole);
                }
            }
            
            // Process each role in the role model
            if (roleModel.getRoles() != null) {
                for (Role role : roleModel.getRoles()) {
                    DictionaryRole dictRole = dictionaryRoleMap.get(role.getCode());
                    
                    if (dictRole == null) {
                        preview.append("Role not found in dictionary: ").append(role.getCode()).append("\n\n");
                        continue;
                    }
                    
                    // 1. Add header with platformCode + roleCode
                    preview.append("=== ");
                    if (dictRole.getSudirRoles() != null && !dictRole.getSudirRoles().isEmpty()) {
                        for (SudirRole sudirRole : dictRole.getSudirRoles()) {
                            preview.append(sudirRole.getPlatformCode())
                                    .append(":")
                                    .append(sudirRole.getRoleCode())
                                    .append(" ");
                        }
                    }
                    preview.append("===\n");
                    
                    // Add role name
                    preview.append("Role: ").append(dictRole.getName()).append("\n\n");
                    
                    // 2. Collect all permissions for this role
                    Set<Permission> rolePermissions = new HashSet<>();
                    
                    // Add direct permissions
                    if (role.getPermissionRefs() != null) {
                        for (String permissionCode : role.getPermissionRefs()) {
                            Permission permission = permissionMap.get(permissionCode);
                            if (permission != null) {
                                rolePermissions.add(permission);
                            }
                        }
                    }
                    
                    // Add permissions from permission groups
                    if (role.getPermissionGroupRefs() != null) {
                        for (String groupCode : role.getPermissionGroupRefs()) {
                            PermissionGroup group = permissionGroupMap.get(groupCode);
                            if (group != null && group.getPermissionRefs() != null) {
                                for (String permissionCode : group.getPermissionRefs()) {
                                    Permission permission = permissionMap.get(permissionCode);
                                    if (permission != null) {
                                        rolePermissions.add(permission);
                                    }
                                }
                            }
                        }
                    }
                    
                    // Display permissions
                    preview.append("Permissions:\n");
                    for (Permission permission : rolePermissions) {
                        preview.append("- ").append(permission.getCode()).append(": ").append(permission.getName()).append("\n");
                    }
                    
                    preview.append("\n--------------------------\n\n");
                }
            }
            
            textArea.setText(preview.toString());
        } catch (Exception e) {
            textArea.setText("Error generating preview: " + e.getMessage());
            e.printStackTrace();
        }
    }
}