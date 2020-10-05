/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package com.presinal.anxeliephotoshoot.security.rolegroup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.presinal.anxeliephotoshoot.security.rolegroup.RestApplicationTest;
import com.presinal.anxeliephotoshoot.security.rolegroup.config.TestApplicationConfig;
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
import com.presinal.anxeliephotoshoot.security.rolegroup.entity.RoleGroup;
import com.presinal.anxeliephotoshoot.security.rolegroup.service.RoleGroupService;
import com.presinal.anxeliephotoshoot.security.rolegroup.searchcriteria.RoleGroupSearchCriteria;

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
public class RoleGroupServiceTest  extends DefaultCrudServiceTest<RoleGroup, RoleGroupSearchCriteria> {

    private static Logger logger = LoggerFactory.getLogger(RoleGroupServiceTest.class);
    private final Long CREATED_BY = 2L;
    
    @Autowired
    private RoleGroupService service;
    
    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    public RoleGroupServiceTest() {

        RoleGroup entityToSave = new RoleGroup();
        entityToSave.setName("PROD-5");
        entityToSave.setCreatedBy(CREATED_BY);
        entityToSave.setUpdatedBy(CREATED_BY);
        
        RoleGroup entityToUpdate = new RoleGroup();
        entityToUpdate.setId(2L);
        entityToUpdate.setName("PROD-6");
        entityToUpdate.setCreatedBy(CREATED_BY);
        entityToUpdate.setUpdatedBy(CREATED_BY);
        
        RoleGroup existingEntity = new RoleGroup();
        existingEntity.setId(2L);
        existingEntity.setName("PROD-2");
        existingEntity.setCreatedBy(CREATED_BY);
        existingEntity.setUpdatedBy(CREATED_BY);

        CrudServiceTestConfig<RoleGroup, RoleGroupSearchCriteria> config = new CrudServiceTestConfig<>();
        config.setDataInstanceToSave(entityToSave);
        config.setDataInstanceToUpdate(entityToUpdate);
        config.setExistingDataInstaceToSave(existingEntity);
        
        config.setFindAllExpectedValue(4); // Change this value
        
        RoleGroupSearchCriteria criterios = new RoleGroupSearchCriteria();        
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
    public void setInsertScript(@Value("data/RoleGroup/RoleGroup-insert_data.sql") String insertScript) {
        super.setInsertScript(insertScript);
    }

    @Override
    @Autowired
    public void setCleanScript(@Value("data/RoleGroup/RoleGroup-clean_data.sql") String cleanScript) {
        super.setCleanScript(cleanScript);
    }

    @Override
    protected RoleGroupService getService() {
        return service;
    }

}
