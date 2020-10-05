/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Apache Velocity variables */

package com.presinal.appsec.role.controller;

import com.presiframework.common.rest.mapper.EntityDtoMapper;
import com.presiframework.common.rest.AbstractCrudRestService;
import com.presiframework.common.rest.CrudRestService;
import com.presiframework.common.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presinal.appsec.role.entity.Role;
import com.presinal.appsec.role.dto.RoleDto;
import com.presinal.appsec.role.dto.mapper.RoleEntityDtoMapper;
import com.presinal.appsec.role.service.RoleService;
import com.presinal.appsec.role.searchcriteria.RoleSearchCriteria;



/**
 * Servicio Rest para la gestion de Role
 * 
 * URI:
 * GET /Role/{id}: Buscar por id.
 * GET /Role: Buscar todo.
 * GET /Role/filtrados: Buscar por criterios de busqueda.
 * GET /Role?nombre: Buscar por nombre
 * POST /Role: Crear.
 * PUT /Role: Actualizar.
 * DELETE /Role/{id}: Eliminar
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping(path = "/role")
public class RoleResource extends AbstractCrudRestService<RoleDto, Role, RoleSearchCriteria> implements CrudRestService<RoleDto, RoleSearchCriteria> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleResource.class);
    
    private final RoleService service;
    private final RoleEntityDtoMapper mapper;

    @Autowired
    public RoleResource(RoleService service, RoleEntityDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public CrudService<Role, RoleSearchCriteria> getServiceProvider() {
        return service;
    }

    @Override
    public EntityDtoMapper<RoleDto, Role> getEntityDtoMapper() {
        return mapper;
    }
}
