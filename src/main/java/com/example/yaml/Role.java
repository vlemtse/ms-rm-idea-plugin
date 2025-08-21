package com.example.yaml;

import java.util.List;

public class Role {
    private String code;
    private String name;
    private List<String> permissionGroupRefs;
    private List<String> permissionRefs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPermissionGroupRefs() {
        return permissionGroupRefs;
    }

    public void setPermissionGroupRefs(List<String> permissionGroupRefs) {
        this.permissionGroupRefs = permissionGroupRefs;
    }

    public List<String> getPermissionRefs() {
        return permissionRefs;
    }

    public void setPermissionRefs(List<String> permissionRefs) {
        this.permissionRefs = permissionRefs;
    }
}