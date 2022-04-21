package org.henry.transformer.mapper;


import org.henry.transformer.UserTransformer;
import org.henry.dto.UserDto;
import org.henry.entity.User;
import org.henry.transformer.mapper.configuration.GenericMapperConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = UserTransformer.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = GenericMapperConfiguration.class)
public interface UserMapper extends BaseMapper<User, UserDto> {
}
