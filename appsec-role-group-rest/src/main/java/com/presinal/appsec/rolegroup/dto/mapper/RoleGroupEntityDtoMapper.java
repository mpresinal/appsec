/* Apache Velocity variables */

package com.presinal.anxeliephotoshoot.security.rolegroup.dto.mapper;

import com.presiframework.common.rest.mapper.BaseEntityDtoMapper;
import org.springframework.stereotype.Component;
import com.presinal.anxeliephotoshoot.security.rolegroup.entity.RoleGroup;
import com.presinal.anxeliephotoshoot.security.rolegroup.dto.RoleGroupDto;

/**
 * Convert RoleGroup to RoleGroupDto and vice versa .
 * 
 * @author Miguel Presinal <presinal378@gmail.com>
 * @since 1.0
 */
@Component
public class RoleGroupEntityDtoMapper extends BaseEntityDtoMapper<RoleGroupDto, RoleGroup> {

    @Override
    public RoleGroupDto newDTO() {
        return new RoleGroupDto();
    }
    
    @Override
    public RoleGroup newEntity() {
        return new RoleGroup();
    }

    @Override
    public void refreshDto(RoleGroupDto dto, RoleGroup entity) {
        super.refreshDto(dto, entity);
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
    }

    @Override
    public void refreshEntity(RoleGroup entity, RoleGroupDto dto) {
        super.refreshEntity(entity, dto);
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
    }
}