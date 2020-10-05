package com.presinal.appsec.role.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion para los webfilter. 
 * El unico proposito de esta clase es, para que se pueda excluir del component scan cuando 
 * el servicio vaya hacer desplegado como una aplicacion monolitica.
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Configuration
@ComponentScan(basePackages = "com.presinal.appsec.role.webfilter")
public class WebfilterConfig {

}
