package dev.rick.mandjesenpuutjesbackend2024.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class UserOutputDTO {

    private String username;
    private String firstname;
    private boolean enabled;
    private UserPreferencesDTO userPreferencesDTO;
    private String[] authorities;
    private long[] savedRecipes;
    private long[] createdRecipes;
}
