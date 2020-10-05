/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/* Apache Velocity variables */

package com.presinal.appsec.role.repository;

import com.presiframework.common.datalayer.jpa.spring.repository.CRUDSpringRepository;
import com.presinal.appsec.role.entity.Role;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface RoleRepository extends CRUDSpringRepository<Role, Long> {

    Role getByNameIgnoreCase(String name);
}
