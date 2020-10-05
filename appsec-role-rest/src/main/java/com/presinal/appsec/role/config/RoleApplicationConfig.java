/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Apache Velocity variables */

package com.presinal.appsec.role.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Configuration
@PropertySource("classpath:/config/Role/application.properties")
@ComponentScan(basePackages = {
    "com.presinal.appsec.role.controller", 
    "com.presinal.appsec.role.dto.mapper",
    "com.presinal.appsec.role.service"    
})
@EnableJpaRepositories(basePackages = "com.presinal.appsec.role.repository")
@EntityScan(basePackages = "com.presinal.appsec.role.entity")
@EnableTransactionManagement
public class RoleApplicationConfig {

}
