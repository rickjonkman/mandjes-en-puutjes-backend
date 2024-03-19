package dev.rick.mandjesenpuutjesbackend2024.recipe;

import dev.rick.mandjesenpuutjesbackend2024.authority.Authority;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.NothingFoundForUserException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Recipe;
import dev.rick.mandjesenpuutjesbackend2024.user.UserRepository;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeConverter recipeConverter;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    void shouldReturnCorrectOutput() {
        String username = "michael@novi.nl";
        User testUser = new User();
        testUser.setUsername(username);

        RecipeInputDTO recipeInputDTO = new RecipeInputDTO();

        when(userRepository.findById(username)).thenReturn(Optional.of(testUser));

        Recipe createdRecipe = new Recipe();
        when(recipeConverter.createRecipe(recipeInputDTO, testUser)).thenReturn(createdRecipe);

        RecipeOutputDTO expectedOutput = new RecipeOutputDTO();
        when(recipeConverter.convertToRecipeOutputDTO(createdRecipe)).thenReturn(expectedOutput);

        RecipeOutputDTO output = recipeService.addRecipe(recipeInputDTO, username);

        verify(recipeRepository, times(1)).save(createdRecipe);
        assertThat(output).isEqualTo(expectedOutput);
    }

    @Test
    void shouldSaveRecipe() {
        String username = "michael@novi.nl";
        User testUser = new User();
        testUser.setUsername(username);

        RecipeInputDTO recipeInputDTO = new RecipeInputDTO();
        recipeInputDTO.setRecipeName("Tosti");
        recipeInputDTO.setServings(4);
        recipeInputDTO.setCreatedByUser(username);

        Recipe createdRecipe = new Recipe();
        createdRecipe.setRecipeName("Tosti");
        createdRecipe.setServings(4);
        createdRecipe.setCreatedByUser(testUser);

        RecipeOutputDTO recipeOutputDTO = new RecipeOutputDTO();
        recipeOutputDTO.setRecipeName("Tosti");
        recipeOutputDTO.setServings(4);
        recipeOutputDTO.setCreatedByUser(testUser.getUsername());

        when(userRepository.findById(username)).thenReturn(Optional.of(testUser));
        when(recipeConverter.createRecipe(recipeInputDTO, testUser)).thenReturn(createdRecipe);
        when(recipeRepository.save(createdRecipe)).thenReturn(createdRecipe);
        when(recipeConverter.convertToRecipeOutputDTO(createdRecipe)).thenReturn(recipeOutputDTO);

        RecipeOutputDTO output = recipeService.addRecipe(recipeInputDTO, username);

        assertThat(output).isNotNull();
        assertThat(output.getRecipeName()).isEqualTo("Tosti");
        assertThat(output.getServings()).isEqualTo(4);
        assertThat(output.getCreatedByUser()).isEqualTo(username);
    }

    @Test
    void shouldReturnRecipeById() {
        long recipeId = 1L;
        String username = "michael@novi.nl";

        User testUser = new User();
        testUser.setUsername(username);

        Recipe foundRecipe = new Recipe();
        foundRecipe.setRecipeId(recipeId);
        foundRecipe.setRecipeName("Tosti");
        foundRecipe.setServings(4);
        foundRecipe.setCreatedByUser(testUser);

        RecipeOutputDTO expected = new RecipeOutputDTO();
        expected.setRecipeName("Tosti");
        expected.setServings(4);
        expected.setCreatedByUser(username);

        when(recipeRepository.findById(recipeId)).thenReturn(Optional.of(foundRecipe));
        when(recipeConverter.convertToRecipeOutputDTO(foundRecipe)).thenReturn(expected);

        RecipeOutputDTO output = recipeService.getRecipeById(recipeId);

        assertThat(output).isNotNull();
        assertThat(output.getRecipeName()).isEqualTo("Tosti");
        assertThat(output.getServings()).isEqualTo(4);
        assertThat(output.getCreatedByUser()).isEqualTo(username);
    }

    @Test
    void shouldThrowRecordNotFound() {
        long idDoesNotExist = 99L;

        when(recipeRepository.findById(idDoesNotExist)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService.getRecipeById(idDoesNotExist))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("ID not found: "+idDoesNotExist);
    }

    @Test
    void shouldReturnAllRecipes() {
        List<Recipe> foundRecipes = new ArrayList<>();

        String usernameOne = "michael@novi.nl";
        String usernameTwo = "anita@novi.nl";

        User creatorOne = new User();
        creatorOne.setUsername(usernameOne);

        User creatorTwo = new User();
        creatorTwo.setUsername(usernameTwo);

        Recipe foundRecipeOne = new Recipe();
        foundRecipeOne.setRecipeId(1L);
        foundRecipeOne.setRecipeName("Tosti");
        foundRecipeOne.setServings(4);
        foundRecipeOne.setCreatedByUser(creatorOne);

        Recipe foundRecipeTwo = new Recipe();
        foundRecipeTwo.setRecipeId(2L);
        foundRecipeTwo.setRecipeName("Hotdogs");
        foundRecipeTwo.setServings(2);
        foundRecipeTwo.setCreatedByUser(creatorTwo);

        RecipeOutputDTO recipeOutputDTO1 = new RecipeOutputDTO();
        recipeOutputDTO1.setRecipeId(1L);
        recipeOutputDTO1.setRecipeName("Tosti");
        recipeOutputDTO1.setServings(4);
        recipeOutputDTO1.setCreatedByUser(usernameOne);

        RecipeOutputDTO recipeOutputDTO2 = new RecipeOutputDTO();
        recipeOutputDTO2.setRecipeId(2L);
        recipeOutputDTO2.setRecipeName("Hotdogs");
        recipeOutputDTO2.setServings(2);
        recipeOutputDTO2.setCreatedByUser(usernameTwo);

        foundRecipes.add(foundRecipeOne);
        foundRecipes.add(foundRecipeTwo);

        List<RecipeOutputDTO> expectedOutput = new ArrayList<>();
        expectedOutput.add(recipeOutputDTO1);
        expectedOutput.add(recipeOutputDTO2);

        when(recipeRepository.findAll()).thenReturn(foundRecipes);
        when(recipeConverter.convertToRecipeOutputDTO(foundRecipeOne)).thenReturn(expectedOutput.get(0));
        when(recipeConverter.convertToRecipeOutputDTO(foundRecipeTwo)).thenReturn(expectedOutput.get(1));

        List<RecipeOutputDTO> output = recipeService.getAllRecipes();

        assertThat(output).isNotNull();
        assertThat(output.size()).isEqualTo(2);
        assertThat(output.get(0)).isEqualTo(expectedOutput.get(0));
        assertThat(output.get(1)).isEqualTo(expectedOutput.get(1));
    }

    // TEST FAILED
    @Test
    void shouldReturnRecipesByCreator() {
        String username = "michael@novi.nl";
        User testUser = new User();
        testUser.setUsername(username);

        List<Recipe> foundRecipes = new ArrayList<>();
        Recipe recipeOne = new Recipe();
        recipeOne.setRecipeId(1L);
        recipeOne.setRecipeName("Tosti");
        recipeOne.setServings(4);
        recipeOne.setCreatedByUser(testUser);

        RecipeOutputDTO expectedRecipeOutput = new RecipeOutputDTO();
        expectedRecipeOutput.setRecipeId(1L);
        expectedRecipeOutput.setRecipeName("Tosti");
        expectedRecipeOutput.setServings(4);
        expectedRecipeOutput.setCreatedByUser(username);

        List<RecipeOutputDTO> expectedOutput = new ArrayList<>();
        expectedOutput.add(expectedRecipeOutput);

        when(userRepository.findById(username)).thenReturn(Optional.of(testUser));
        when(recipeRepository.findAllByCreatedByUser_Username(username)).thenReturn(Optional.of(foundRecipes));
        when(recipeConverter.convertToRecipeOutputDTO(recipeOne)).thenReturn(expectedOutput.get(0));

        List<RecipeOutputDTO> outputList = recipeService.getRecipesByCreator(username);

        assertThat(outputList).isNotNull();
        assertThat(outputList.size()).isEqualTo(1);
        assertThat(outputList.get(0)).isEqualTo(expectedOutput.get(0));
    }

    @Test
    void shouldThrowRecordNotFoundForUser() {
        String username = "marieke@novi.nl";

        when(userRepository.findById(username)).thenReturn(Optional.of(new User()));
        when(recipeRepository.findAllByCreatedByUser_Username(username)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> recipeService
                .getRecipesByCreator(username))
                .isInstanceOf(NothingFoundForUserException.class)
                .hasMessageContaining("Nothing found for user: "+username);
    }


}