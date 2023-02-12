package com.example.usercrud.service;

import com.example.usercrud.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto addUser(UserDto user);
    UserDto updateUser(UserDto user, Long id);
    void deleteUserById(Long id);
}
