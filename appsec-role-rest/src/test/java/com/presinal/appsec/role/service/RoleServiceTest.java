/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.presinal.appsec.role.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.presinal.appsec.role.RestApplicationTest;
import com.presinal.appsec.role.config.TestApplicationConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.core.io.ClassPathResource;
import java.sql.Connection;
import javax.persistence.EntityManagerFactory;
import org.hibernate.Session;
import com.presiframework.common.test.DefaultCrudServiceTest;
import com.presiframework.common.test.CrudServiceTestConfig;
import com.presiframework.common.datalayer.entities.enums.EntityStatus;
import com.presinal.appsec.role.entity.Role;
import com.presinal.appsec.role.service.RoleService;
import com.presinal.appsec.role.searchcriteria.RoleSearchCriteria;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RestApplicationTest.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application.properties")
public class RoleServiceTest  extends DefaultCrudServiceTest<Role, RoleSearchCriteria> {

    private static Logger logger = LoggerFactory.getLogger(RoleServiceTest.class);
    private final Long CREATED_BY = 2L;
    
    @Autowired
    private RoleService service;
    
    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    public RoleServiceTest() {

        Role entityToSave = new Role();
        entityToSave.setName("PROD-5");
        entityToSave.setCreatedBy(CREATED_BY);
        entityToSave.setUpdatedBy(CREATED_BY);
        
        Role entityToUpdate = new Role();
        entityToUpdate.setId(2L);
        entityToUpdate.setName("PROD-6");
        entityToUpdate.setCreatedBy(CREATED_BY);
        entityToUpdate.setUpdatedBy(CREATED_BY);
        
        Role existingEntity = new Role();
        existingEntity.setId(2L);
        existingEntity.setName("PROD-2");
        existingEntity.setCreatedBy(CREATED_BY);
        existingEntity.setUpdatedBy(CREATED_BY);

        CrudServiceTestConfig<Role, RoleSearchCriteria> config = new CrudServiceTestConfig<>();
        config.setDataInstanceToSave(entityToSave);
        config.setDataInstanceToUpdate(entityToUpdate);
        config.setExistingDataInstaceToSave(existingEntity);
        
        config.setFindAllExpectedValue(4); // Change this value
        
        RoleSearchCriteria criterios = new RoleSearchCriteria();        
        criterios.setStatus(EntityStatus.INACTIVE.getValue()); // change this value according to your neeeds
        config.setSearchCriteriaStateIN(criterios);
        config.setFindAllFlowSearchCriteriaStateINExpectedValue(2);  // change this value according to your needs
        
        // Disabling all test. enable it according to your needs        
        config.setEnableTestExistingDataInstance(false);
        config.setEnableTestsFlowRequiredFieldCreatedBy(false);
        config.setEnableTestsFlowIntegrityViolation(false);
        config.setEnableTestsFlowExistingData(false);
        config.setEnableTestsCreateFlowExistingData(false);
        config.setEnableTestFlowSearchCriteriaStateIN(false);
        
        setTestConfig(config);
       
    }

    @Override
    public void setUp() {
        final String METHOD = "setUp() :: ";
        Logger logger = getLogger();
        logger.info(METHOD + "Enter");
        
        Session hibernateSession = entityManagerFactory.createEntityManager().unwrap(Session.class);
        hibernateSession.doWork((Connection connection) -> {

            logger.info(METHOD + "Cleaning up data....");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource(getCleanScript()));
            logger.info(METHOD + "Cleaning up data....DONE!!");

            logger.info(METHOD + "Initializing data....");
            ScriptUtils.executeSqlScript(connection, new ClassPathResource(getInsertScript()));
            logger.info(METHOD + "Initializing data....DONE!!");
        });

        logger.info(METHOD + "Exit");
    }
    
    @Override
    protected Logger getLogger() {
        return logger;
    }

    @Override
    @Autowired
    public void setInsertScript(@Value("data/Role/Role-insert_data.sql") String insertScript) {
        super.setInsertScript(insertScript);
    }

    @Override
    @Autowired
    public void setCleanScript(@Value("data/Role/Role-clean_data.sql") String cleanScript) {
        super.setCleanScript(cleanScript);
    }

    @Override
    protected RoleService getService() {
        return service;
    }

}
