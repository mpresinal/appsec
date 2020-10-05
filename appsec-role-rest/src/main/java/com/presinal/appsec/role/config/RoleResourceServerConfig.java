/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.presinal.appsec.role.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Configuration
@EnableResourceServer
public class RoleResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${resource.server.id}")
    private String resourceServerId;
    
    @Value("${as.client}")
    private String authServerClientId;
    
    @Value("${as.key}")
    private String authServerClientSecret;
    
    @Value("${as.checktoken.uri}")
    private String checkTokenEndpoint;
    
    @Value("${server.servlet.context-path}")
    private String contextPath;
    
    @Bean
    public RemoteTokenServices tokenServices() {
        RemoteTokenServices service = new RemoteTokenServices();
        service.setClientId(authServerClientId);
        service.setClientSecret(authServerClientSecret);
        service.setCheckTokenEndpointUrl(checkTokenEndpoint);
        return service;
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        new RSConfigurer().configureSecurity(http, contextPath);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {        
        resources.resourceId(resourceServerId).tokenServices(tokenServices());
    }
    
    /**
     * Inner class to configure the resources server endpoints security
     */
    public static final class RSConfigurer {

        public void configureSecurity(HttpSecurity http, String contextPath) throws Exception {            
            http.csrf().disable()
                .authorizeRequests()
                .antMatchers(contextPath+"/role") // this must be changed 
                .hasAuthority(""); // this must be changed 
        }
    }
}
