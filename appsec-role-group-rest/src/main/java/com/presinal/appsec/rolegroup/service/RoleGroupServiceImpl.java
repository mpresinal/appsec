/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup.service;

import com.presiframework.common.datalayer.entities.exceptions.BaseException;
import com.presiframework.common.datalayer.entities.exceptions.RequiredFieldException;
import com.presiframework.common.service.exception.InternalServiceException;
import com.presiframework.common.datalayer.entities.exceptions.NoDataFoundException;
import com.presiframework.commons.service.jpa.spring.AbstractJpaSpringCrudService;
import com.presiframework.common.datalayer.entities.validator.EntityValidator;
import com.presiframework.common.datalayer.entities.validator.AbstractEntityValidatorBeanValidation;
import com.presiframework.common.datalayer.entities.validator.IntegrityValidator;

import com.presiframework.common.datalayer.entities.validator.util.EntityValidatorUtil;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.presinal.anxeliephotoshoot.security.rolegroup.entity.RoleGroup;
import com.presinal.anxeliephotoshoot.security.rolegroup.repository.RoleGroupRepository;
import com.presinal.anxeliephotoshoot.security.rolegroup.searchcriteria.RoleGroupSearchCriteria;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Service
@Transactional
public class RoleGroupServiceImpl extends AbstractJpaSpringCrudService<RoleGroup, RoleGroupSearchCriteria> implements RoleGroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleGroupServiceImpl.class);

    private RoleGroupRepository repository;
    private EntityValidator<RoleGroup> entityValidator;
    
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    @Transactional(readOnly = true)
    public RoleGroup findByNombre(String name) throws RequiredFieldException, NoDataFoundException, InternalServiceException {
        final String METHOD = "findByNombre():: ";
        LOGGER.info(METHOD + "Enter");
        LOGGER.info(METHOD + "name = " + name);

        try {
            EntityValidatorUtil.validateNotNullNotEmpty(name, "name");
            RoleGroup entity = repository.getByNameIgnoreCase(name);
            if (entity == null) {
                throw new NoDataFoundException();
            }

            LOGGER.info(METHOD + "Returning entity = " + entity);
            return entity;
        } catch (BaseException e) {
            LOGGER.info(METHOD + "throwing exception: " + e.getClass().getSimpleName());
            throw e;

        } finally {
            LOGGER.info(METHOD + "Exit");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleGroup> findByStatus(String status) throws InternalServiceException {
        final String METHOD = "findByStatus():: ";
        LOGGER.info(METHOD + "Enter");
        LOGGER.info(METHOD + "status = " + status);

        try {
            RoleGroupSearchCriteria filter = new RoleGroupSearchCriteria();
            filter.setStatus(status);
            return super.filter(filter);
        } finally {
            LOGGER.info(METHOD + "Exit");
        }
    }

    @Override
    public IntegrityValidator<RoleGroup> getIntegrityValidator() {        
        return null;
    }

    @Override
    public EntityValidator<RoleGroup> getEntityValidator() {            
        if (entityValidator == null) {
            entityValidator = new AbstractEntityValidatorBeanValidation<RoleGroup>() {
                @Override
                public List<?> getEntityReferences(RoleGroup entity) {
                    return null;
                }
            };
        }

        return entityValidator;
    }

    @Override
    public RoleGroupRepository getRepository() {
        return repository;
    }

    @Autowired
    public void setRepository(RoleGroupRepository repository) {
        this.repository = repository;
    }

}
