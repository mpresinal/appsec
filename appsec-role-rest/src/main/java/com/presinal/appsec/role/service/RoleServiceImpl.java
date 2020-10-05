/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Apache Velocity variables */

package com.presinal.appsec.role.service;

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
import com.presinal.appsec.role.entity.Role;
import com.presinal.appsec.role.repository.RoleRepository;
import com.presinal.appsec.role.searchcriteria.RoleSearchCriteria;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractJpaSpringCrudService<Role, RoleSearchCriteria> implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private RoleRepository repository;
    private EntityValidator<Role> entityValidator;
    
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    @Transactional(readOnly = true)
    public Role findByNombre(String name) throws RequiredFieldException, NoDataFoundException, InternalServiceException {
        final String METHOD = "findByNombre():: ";
        LOGGER.info(METHOD + "Enter");
        LOGGER.info(METHOD + "name = " + name);

        try {
            EntityValidatorUtil.validateNotNullNotEmpty(name, "name");
            Role entity = repository.getByNameIgnoreCase(name);
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
    public List<Role> findByStatus(String status) throws InternalServiceException {
        final String METHOD = "findByStatus():: ";
        LOGGER.info(METHOD + "Enter");
        LOGGER.info(METHOD + "status = " + status);

        try {
            RoleSearchCriteria filter = new RoleSearchCriteria();
            filter.setStatus(status);
            return super.filter(filter);
        } finally {
            LOGGER.info(METHOD + "Exit");
        }
    }

    @Override
    public IntegrityValidator<Role> getIntegrityValidator() {        
        return null;
    }

    @Override
    public EntityValidator<Role> getEntityValidator() {            
        if (entityValidator == null) {
            entityValidator = new AbstractEntityValidatorBeanValidation<Role>() {
                @Override
                public List<?> getEntityReferences(Role entity) {
                    return null;
                }
            };
        }

        return entityValidator;
    }

    @Override
    public RoleRepository getRepository() {
        return repository;
    }

    @Autowired
    public void setRepository(RoleRepository repository) {
        this.repository = repository;
    }

}
