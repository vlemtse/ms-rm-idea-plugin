package com.example.yaml;

import java.util.List;

public class PermissionGroup {
    private String code;
    private String name;
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

    public List<String> getPermissionRefs() {
        return permissionRefs;
    }

    public void setPermissionRefs(List<String> permissionRefs) {
        this.permissionRefs = permissionRefs;
    }
}