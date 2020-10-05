/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup.service;

import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import com.presiframework.common.service.exception.InternalServiceException;
import com.presiframework.common.datalayer.entities.exceptions.NoDataFoundException;
import com.presiframework.commons.service.jpa.spring.JpaSpringCrudService;
import java.util.List;
import com.presinal.anxeliephotoshoot.security.rolegroup.entity.RoleGroup;
import com.presinal.anxeliephotoshoot.security.rolegroup.searchcriteria.RoleGroupSearchCriteria;


/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
public interface RoleGroupService extends JpaSpringCrudService<RoleGroup, RoleGroupSearchCriteria> {

    RoleGroup findByNombre(String name) throws RequiredFieldException, NoDataFoundException, InternalServiceException;

    List<RoleGroup> findByStatus(String status) throws InternalServiceException;
}
