package org.henry.transformer.mapper;

import org.henry.dto.BaseDto;
import org.henry.entity.BaseEntity;
import org.mapstruct.MappingTarget;

public interface BaseMapper<Entity extends BaseEntity, Dto extends BaseDto> {

    Entity dtoToEntity(Dto dto);

    Dto entityToDto(Entity entity);

    void updateEntity(Dto dto, @MappingTarget Entity entity);
}
