package dev.rick.mandjesenpuutjesbackend2024.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserShortOutputDTO {

    private String username;
    private boolean enabled;
    private String firstname;
    private UserPreferencesDTO preferences;
}