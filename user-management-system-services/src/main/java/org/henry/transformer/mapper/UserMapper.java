package org.henry.transformer.mapper;


import org.henry.dto.UserDto;
import org.henry.entity.User;
import org.henry.transformer.mapper.configuration.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
