/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup.config;

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
@PropertySource("classpath:/config/RoleGroup/application.properties")
@ComponentScan(basePackages = {
    "com.presinal.anxeliephotoshoot.security.rolegroup.controller", 
    "com.presinal.anxeliephotoshoot.security.rolegroup.dto.mapper",
    "com.presinal.anxeliephotoshoot.security.rolegroup.service"    
})
@EnableJpaRepositories(basePackages = "com.presinal.anxeliephotoshoot.security.rolegroup.repository")
@EntityScan(basePackages = "com.presinal.anxeliephotoshoot.security.rolegroup.entity")
@EnableTransactionManagement
public class RoleGroupApplicationConfig {

}
