/* Apache Velocity  */

package com.presinal.appsec.role.webfilter;

import com.presiframework.common.rest.filter.SimpleCorsFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Este filter autoriza los preflith de los cors request generado por los
 * navegadorres web cuando realiza una peticion a un recurso de otro dominio.
 *
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RoleCorsFilter extends SimpleCorsFilter {

    @Autowired
    public RoleCorsFilter(
            @Value("#{'${cors.allowed.origins}'.split(',')}") List<String> allowedOrigings,
            @Value("${cors.allowed.headers}") String allowedHeaders) {
        super(allowedOrigings, allowedHeaders);
    }    
}
