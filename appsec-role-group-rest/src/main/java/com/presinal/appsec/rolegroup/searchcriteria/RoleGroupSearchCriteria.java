/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup.searchcriteria;

import com.presiframework.common.datalayer.jpa.spring.repository.filter.ExampleSearchCriteria;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import com.presinal.anxeliephotoshoot.security.rolegroup.entity.RoleGroup;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public class RoleGroupSearchCriteria implements ExampleSearchCriteria<RoleGroup> {

    private String name;
    private String status;

    @Override
    public ExampleMatcher getMatcher() {
        return ExampleMatcher.matchingAll()
                .withIgnoreCase()
                .withMatcher("name", GenericPropertyMatcher.of(StringMatcher.CONTAINING));  
    }
    
    @Override
    public RoleGroup getEntity() {
        RoleGroup entity = new RoleGroup();
        entity.setName(name);
        entity.setStrStatus(status);
        return entity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() +"{" + "name=" + name + ", status=" + status + '}';
    }
}
