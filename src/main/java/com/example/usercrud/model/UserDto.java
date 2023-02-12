package com.example.usercrud.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    @NotNull
    @NotBlank(message = "First name can not be blank.")
    @Size(min = 3, max = 30, message = "First name can not be less than 3 or more than 30.")
    @Pattern(regexp="^([A-Za-z])+$", message = "No spaces, numbers, specific signs or symbols.")
    private String firstName;
    @NotNull
    @NotBlank(message = "Last name can not be blank.")
    @Size(min = 3, max = 30, message = "Last name can not be less than 3 or more than 30.")
    @Pattern(regexp="^([A-Za-z])+$", message = "No spaces, numbers, specific signs or symbols.")
    private String lastName;
    @NotNull
    @Email
    @NotBlank(message = "Email can not be blank.")
    private String email;

}
