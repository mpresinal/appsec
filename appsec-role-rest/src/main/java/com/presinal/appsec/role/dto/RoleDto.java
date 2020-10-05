/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presinal.appsec.role.dto;

import com.presiframework.common.rest.dto.CommonDto;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class RoleDto extends CommonDto {

    private static final String OBJECT_NAME = "Role";

    private String name; 
    private String description;

    public RoleDto() {
        super(OBJECT_NAME);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    @Override
    public String toString() {
        String properties = ", name=" + name + ", description=" + description;
        return super.toString(getClass().getSimpleName(), properties);
    }
}
