package dev.rick.mandjesenpuutjesbackend2024.user.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserPreferencesDTO {

    private boolean showMeat;
    private boolean showFish;
    private boolean showVegetarian;
    private boolean showVegan;
}
