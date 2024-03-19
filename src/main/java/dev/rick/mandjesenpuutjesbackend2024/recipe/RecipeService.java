package dev.rick.mandjesenpuutjesbackend2024.recipe;

import dev.rick.mandjesenpuutjesbackend2024.exceptions.NotAuthorizedException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.NothingFoundForUserException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Diet;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Recipe;
import dev.rick.mandjesenpuutjesbackend2024.user.UserRepository;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepo;
    private final UserRepository userRepository;
    private final RecipeConverter converter;

    public RecipeOutputDTO addRecipe(RecipeInputDTO recipeInputDTO, String username) {
        User foundUser = findUserByUsername(username);

        Recipe createdRecipe = converter.createRecipe(recipeInputDTO, foundUser);
        recipeRepo.save(createdRecipe);

        return converter.convertToRecipeOutputDTO(createdRecipe);
    }

    public List<RecipeOutputDTO> getAllRecipes() {
        List<Recipe> foundRecipes = recipeRepo.findAll();

        List<RecipeOutputDTO> recipeOutputList = new ArrayList<>();
        for (Recipe foundRecipe : foundRecipes) {
            RecipeOutputDTO singleRecipeDTO = converter.convertToRecipeOutputDTO(foundRecipe);
            recipeOutputList.add(singleRecipeDTO);
        }
        return recipeOutputList;
    }

    public RecipeOutputDTO getRecipeById(long id) {
        Optional<Recipe> optionalRecipe = recipeRepo.findById(id);
        if (optionalRecipe.isEmpty()) {
            throw new RecordNotFoundException(id);
        } else {
            return converter.convertToRecipeOutputDTO(optionalRecipe.get());
        }
    }

    public List<RecipeOutputDTO> getRecipesByCreator(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser == null) {
            throw new RecordNotFoundException(username);
        } else {
            Optional<List<Recipe>> optionalRecipes = recipeRepo.findAllByCreatedByUser_Username(username);
            if (optionalRecipes.isEmpty()) {
                throw new NothingFoundForUserException(username);
            } else {
                List<RecipeOutputDTO> recipeOutputList = new ArrayList<>();
                for (Recipe foundRecipe : optionalRecipes.get()) {
                    RecipeOutputDTO singleRecipeDTO = converter.convertToRecipeOutputDTO(foundRecipe);
                    recipeOutputList.add(singleRecipeDTO);
                }
                return recipeOutputList;
            }
        }
    }

    public RecipeOutputDTO updateRecipeById(long id, RecipeInputDTO recipeInputDTO, Principal principal) {
        RecipeOutputDTO outputDTO = new RecipeOutputDTO();

        Optional<Recipe> optionalRecipe = recipeRepo.findById(id);
        if (optionalRecipe.isEmpty()) {
            throw new RecordNotFoundException(id);
        } else {
            Recipe foundRecipe = optionalRecipe.get();

            if (foundRecipe.getCreatedByUser().equals(principal)) {
                Recipe updatedRecipe = converter.updateRecipe(foundRecipe, recipeInputDTO);
                recipeRepo.save(updatedRecipe);

                outputDTO = converter.convertToRecipeOutputDTO(updatedRecipe);
            }
        }

        return outputDTO;
    }

    public void deleteRecipeById(long id, Principal principal) {
        Optional<Recipe> optionalRecipe = recipeRepo.findById(id);
        if (optionalRecipe.isEmpty()) {
            throw new RecordNotFoundException(id);
        } else {
            Recipe recipe = optionalRecipe.get();

            if (recipe.getCreatedByUser().equals(principal)) {
                recipeRepo.deleteById(id);
            } else {
                throw new NotAuthorizedException();
            }
        }
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        return optionalUser.orElse(null);
    }
}
