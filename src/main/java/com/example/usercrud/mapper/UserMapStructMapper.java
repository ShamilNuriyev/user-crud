package com.example.usercrud.mapper;

import com.example.usercrud.model.UserDto;
import com.example.usercrud.model.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapStructMapper {
    UserDto userToDto(UserEntity entity);
    UserEntity userToEntity(UserDto dto);
}
