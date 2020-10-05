/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presinal.anxeliephotoshoot.security.rolegroup.entity;

import com.presiframework.common.datalayer.jpa.entity.JpaCommonEntity;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Where;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Entity
@Table(name = "role_group_role")
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "role_group_role_id"))
})
@Access(AccessType.PROPERTY)
@Where(clause = "deleted = false")
public class RoleGroupRole extends JpaCommonEntity {

    @NotNull
    private RoleGroup group;
    private Long groupId;
    
    @NotNull
    private Long roleId;
    
    public RoleGroupRole() {
    }

    public RoleGroupRole(Long id) {
        super(id);
    }

    @JoinColumn(name = "role_group_id", nullable = false)
    @ManyToOne
    public RoleGroup getGroup() {
        return group;
    }

    public void setGroup(RoleGroup group) {
        this.group = group;
    }

    @Column(name = "role_group_id", insertable = false, updatable = false)
    public Long getGroupId() {
        return groupId;
    }

    @Column(name = "role_id", nullable = false)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        String properties = ", group=" + group.getId() + ", roleId=" + roleId;
        return super.toString(getClass().getSimpleName(), properties);
    }
}
