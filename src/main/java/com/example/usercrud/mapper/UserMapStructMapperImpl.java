package com.example.usercrud.mapper;


import com.example.usercrud.model.UserDto;
import com.example.usercrud.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapStructMapperImpl implements UserMapStructMapper{
    public UserDto userToDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        return dto;
    }
    public UserEntity userToEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

}
