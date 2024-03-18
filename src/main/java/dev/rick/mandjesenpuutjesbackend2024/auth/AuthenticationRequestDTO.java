package dev.rick.mandjesenpuutjesbackend2024.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AuthenticationRequestDTO {

    private String username;
    private String password;


}