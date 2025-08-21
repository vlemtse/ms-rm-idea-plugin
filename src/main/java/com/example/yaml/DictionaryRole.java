package com.example.yaml;

import java.util.List;

public class DictionaryRole {
    private String code;
    private String name;
    private List<SudirRole> sudirRoles;
    private List<String> description;

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

    public List<SudirRole> getSudirRoles() {
        return sudirRoles;
    }

    public void setSudirRoles(List<SudirRole> sudirRoles) {
        this.sudirRoles = sudirRoles;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }
}