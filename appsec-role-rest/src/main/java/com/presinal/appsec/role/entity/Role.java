/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presinal.appsec.role.entity;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Entity
@Table(name = "role")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "role_id"))
})
@Access(AccessType.PROPERTY)
@Where(clause = "deleted = false")
public class Role extends JpaCommonEntity {

    @NotEmpty
    @Size(max = 100)
    private String name;
    
    @NotEmpty
    @Size(max = 256)
    private String description;
    
    public Role() {
    }

    public Role(Long id) {
        super(id);
    }
    
    @Column(name = "name", length = 100, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    

    @Column(name = "description", length = 256, nullable = false)
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
