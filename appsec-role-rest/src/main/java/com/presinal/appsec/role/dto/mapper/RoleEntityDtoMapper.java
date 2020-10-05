/* Apache Velocity variables */

package com.presinal.appsec.role.dto.mapper;

import com.presiframework.common.rest.mapper.BaseEntityDtoMapper;
import org.springframework.stereotype.Component;
import com.presinal.appsec.role.entity.Role;
import com.presinal.appsec.role.dto.RoleDto;

/**
 * Convert Role to RoleDto and vice versa .
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Component
public class RoleEntityDtoMapper extends BaseEntityDtoMapper<RoleDto, Role> {

    @Override
    public RoleDto newDTO() {
        return new RoleDto();
    }
    
    @Override
    public Role newEntity() {
        return new Role();
    }

    @Override
    public void refreshDto(RoleDto dto, Role entity) {
        super.refreshDto(dto, entity);
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
    }

    @Override
    public void refreshEntity(Role entity, RoleDto dto) {
        super.refreshEntity(entity, dto);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}