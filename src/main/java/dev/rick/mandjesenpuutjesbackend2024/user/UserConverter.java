package dev.rick.mandjesenpuutjesbackend2024.user;

import dev.rick.mandjesenpuutjesbackend2024.authority.Authority;
import dev.rick.mandjesenpuutjesbackend2024.authority.AuthorityDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Recipe;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserPreferencesDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserShortOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import dev.rick.mandjesenpuutjesbackend2024.user.models.UserPreferences;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Component
public class UserConverter {

    private final PasswordEncoder encoder;

    public User convertToUser(UserInputDTO inputDTO) {
        User newUser = new User();
        newUser.setUsername(inputDTO.getUsername());
        newUser.setPassword(encoder.encode(inputDTO.getPassword()));
        newUser.setFirstname(inputDTO.getFirstname());
        newUser.setEnabled(inputDTO.isEnabled());
        newUser.setPreferences(convertToUserPreferences(inputDTO.getPreferences()));
        return newUser;
    }

    public UserOutputDTO convertToUserOutput(User user) {
        UserOutputDTO newUser = new UserOutputDTO();
        newUser.setUsername(user.getUsername());
        newUser.setFirstname(user.getFirstname());
        newUser.setEnabled(user.isEnabled());
        newUser.setUserPreferencesDTO(convertToUserPreferencesDTO(user.getPreferences()));
        newUser.setSavedRecipes(convertSavedRecipesToArrayID(user.getSavedRecipes()));
        newUser.setCreatedRecipes(convertSavedRecipesToArrayID(user.getCreatedRecipes()));
        newUser.setAuthorities(convertAuthoritiesToArray(user.getAuthorities()));
        return newUser;
    }

    public UserPreferences convertToUserPreferences(UserPreferencesDTO userPreferencesDTO) {
        UserPreferences newUserPreferences = new UserPreferences();
        newUserPreferences.setShowMeat(userPreferencesDTO.isShowMeat());
        newUserPreferences.setShowFish(userPreferencesDTO.isShowFish());
        newUserPreferences.setShowVegetarian(userPreferencesDTO.isShowVegetarian());
        newUserPreferences.setShowVegan(userPreferencesDTO.isShowVegan());
        return newUserPreferences;
    }

    public UserPreferencesDTO convertToUserPreferencesDTO(UserPreferences userPreferences) {
        UserPreferencesDTO newUserPreferencesDTO = new UserPreferencesDTO();
        newUserPreferencesDTO.setShowMeat(userPreferences.isShowMeat());
        newUserPreferencesDTO.setShowFish(userPreferences.isShowFish());
        newUserPreferencesDTO.setShowVegetarian(userPreferences.isShowVegetarian());
        newUserPreferencesDTO.setShowVegan(userPreferences.isShowVegan());
        return newUserPreferencesDTO;
    }

    public UserShortOutputDTO convertToShortOutputDTO(User user) {
        UserShortOutputDTO newUserShortOutputDTO = new UserShortOutputDTO();
        newUserShortOutputDTO.setUsername(user.getUsername());
        newUserShortOutputDTO.setFirstname(user.getFirstname());
        newUserShortOutputDTO.setEnabled(user.isEnabled());
        newUserShortOutputDTO.setPreferences(convertToUserPreferencesDTO(user.getPreferences()));
        return newUserShortOutputDTO;
    }

    public String[] convertAuthoritiesToArray(Set<Authority> authorities) {
        String[] authArray = new String[authorities.size()];

        List<String> authNames = new ArrayList<>();
        for (Authority authority : authorities) {
            String authName = authority.getAuthority();
            authNames.add(authName);
        }

        authNames.toArray(authArray);
        return authArray;
    }

    public Authority convertToAuthority(AuthorityDTO authorityDTO) {
        return new Authority(authorityDTO.getUsername(), authorityDTO.getAuthority());
    }

    public long[] convertSavedRecipesToArrayID(List<Recipe> recipes) {
        long[] recipeIDArray = new long[recipes.size()];
        int index = 0;

        for (Recipe recipe : recipes) {
            recipeIDArray[index++] = recipe.getRecipeId();
        }

        return recipeIDArray;
    }
}
