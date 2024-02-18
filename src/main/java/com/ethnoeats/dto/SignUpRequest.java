package com.ethnoeats.dto;

import com.ethnoeats.user.model.User;
import lombok.Data;

@Data
public class SignUpRequest extends User {
    private String username;
    private String password;
    private String email;


}
