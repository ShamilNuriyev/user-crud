package com.example.usercrud.service;

import com.example.usercrud.exception.UserNotFound;
import com.example.usercrud.mapper.UserMapStructMapper;
import com.example.usercrud.model.UserDto;
import com.example.usercrud.model.UserEntity;
import com.example.usercrud.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapStructMapper userMapStructMapper;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapStructMapper userMapStructMapper) {
        this.userRepository = userRepository;
        this.userMapStructMapper = userMapStructMapper;
    }
    @Override
    public UserDto getUserById(Long id){
        UserEntity userEntity = checkUserPresence(id);
        return userMapStructMapper.userToDto(userEntity);
    }
    @Override
    public List<UserDto> getAllUsers(){
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userMapStructMapper::userToDto)
                .collect(Collectors.toList());
    }
    @Override
    public UserDto addUser(UserDto user){
        UserEntity UserEntity = userMapStructMapper.userToEntity(user);
        UserEntity savedUser = userRepository.save(UserEntity);
        return userMapStructMapper.userToDto(savedUser);}

    @Override
    public UserDto updateUser(UserDto user, Long id) {
        UserEntity userEntity = checkUserPresence(id);
        if(!Objects.equals(userEntity.getFirstName(), userMapStructMapper.userToEntity(user).getFirstName())) {
            userEntity.setFirstName(userMapStructMapper.userToEntity(user).getFirstName());
        }
        if(!Objects.equals(userEntity.getLastName(), userMapStructMapper.userToEntity(user).getLastName())) {
            userEntity.setLastName(userMapStructMapper.userToEntity(user).getLastName());
        }
        if(!Objects.equals(userEntity.getEmail(), userMapStructMapper.userToEntity(user).getEmail())) {
            userEntity.setEmail(userMapStructMapper.userToEntity(user).getEmail());
        }
        UserEntity updatedUser = userRepository.save(userEntity);


        return userMapStructMapper.userToDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id){
        checkUserPresence(id);
        userRepository.deleteById(id);
    }
    public UserEntity checkUserPresence(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFound("User not found"));
    }
}
