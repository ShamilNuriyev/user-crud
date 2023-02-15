package com.example.usercrud.controller;

import com.example.usercrud.model.UserDto;
import com.example.usercrud.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:63342")
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto UserDto = userService.getUserById(id);
        return new ResponseEntity<>(UserDto, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserDto> addUser(@Valid @RequestBody UserDto user) {
        UserDto UserDto = userService.addUser(user);
        return new ResponseEntity<>(UserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto user, @PathVariable("id") Long id) {
        UserDto UserDto = userService.updateUser(user, id);
        return new ResponseEntity<>(UserDto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    
}
