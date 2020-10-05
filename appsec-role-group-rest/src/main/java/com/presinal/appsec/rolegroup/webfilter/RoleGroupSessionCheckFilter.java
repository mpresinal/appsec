/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* Apache Velocity  */

package com.presinal.anxeliephotoshoot.security.rolegroup.webfilter;

import com.presiframework.common.rest.filter.SessionCheckFilter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Component
@Order(value = SessionCheckFilter.ORDER)
public class RoleGroupSessionCheckFilter extends SessionCheckFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass()); 

    //@Autowired
    public RoleGroupSessionCheckFilter(
            @Value("#{'${sessioncheckfilter.excluded.path}'.split(',')}") List<String> excludedUrlList,
            @Value("${sesioncheck.endpoint}")
            String sesionCheckEndPointUrl) {
        super(excludedUrlList);
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    protected String extractNombreUsuario(HttpServletRequest request) {
        OAuth2Authentication oauth = getOAuth2Authentication(request);
        if (oauth != null) {
            return oauth.getPrincipal().toString();
        }

        return null;
    }

    @Override
    protected String extractTokenAunteticacion(HttpServletRequest request) {
        OAuth2Authentication oauth = getOAuth2Authentication(request);
        if (oauth != null) {
            return ((OAuth2AuthenticationDetails) oauth.getDetails()).getTokenValue();
        }

        return null;
    }

    private OAuth2Authentication getOAuth2Authentication(HttpServletRequest request) {
        Object reqPrincipal = request.getUserPrincipal();
        if (reqPrincipal instanceof OAuth2Authentication) {
            return (OAuth2Authentication) reqPrincipal;
        }

        return null;
    }

    /**
     * Verifica el estado de la sesion del usuario.
     *
     * @return true si la sesion aun esta activa. De lo contrario false
     */
    @Override
    protected Boolean checkEstadoSesion(String nombreUsuario, String token) throws Exception {
        // TODO: Pending implementation
        return true;
    }   
    
}
