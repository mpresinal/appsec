/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup.controller;

import com.presiframework.common.rest.mapper.EntityDtoMapper;
import com.presiframework.common.rest.AbstractCrudRestService;
import com.presiframework.common.rest.CrudRestService;
import com.presiframework.common.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presinal.anxeliephotoshoot.security.rolegroup.entity.RoleGroup;
import com.presinal.anxeliephotoshoot.security.rolegroup.dto.RoleGroupDto;
import com.presinal.anxeliephotoshoot.security.rolegroup.dto.mapper.RoleGroupEntityDtoMapper;
import com.presinal.anxeliephotoshoot.security.rolegroup.service.RoleGroupService;
import com.presinal.anxeliephotoshoot.security.rolegroup.searchcriteria.RoleGroupSearchCriteria;



/**
 * Servicio Rest para la gestion de RoleGroup
 * 
 * URI:
 * GET /RoleGroup/{id}: Buscar por id.
 * GET /RoleGroup: Buscar todo.
 * GET /RoleGroup/filtrados: Buscar por criterios de busqueda.
 * GET /RoleGroup?nombre: Buscar por nombre
 * POST /RoleGroup: Crear.
 * PUT /RoleGroup: Actualizar.
 * DELETE /RoleGroup/{id}: Eliminar
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@RestController
@RequestMapping(path = "/rolegroup")
public class RoleGroupResource extends AbstractCrudRestService<RoleGroupDto, RoleGroup, RoleGroupSearchCriteria> implements CrudRestService<RoleGroupDto, RoleGroupSearchCriteria> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleGroupResource.class);
    
    private final RoleGroupService service;
    private final RoleGroupEntityDtoMapper mapper;

    @Autowired
    public RoleGroupResource(RoleGroupService service, RoleGroupEntityDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public CrudService<RoleGroup, RoleGroupSearchCriteria> getServiceProvider() {
        return service;
    }

    @Override
    public EntityDtoMapper<RoleGroupDto, RoleGroup> getEntityDtoMapper() {
        return mapper;
    }
}
