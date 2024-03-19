package dev.rick.mandjesenpuutjesbackend2024.recipe;

import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.*;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.*;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecipeConverter {

    public Recipe createRecipe(RecipeInputDTO recipeInputDTO, User user) {
        Recipe recipe = new Recipe();
        recipe.setRecipeName(recipe.getRecipeName());
        recipe.setServings(recipeInputDTO.getServings());
        recipe.setPrepTime(convertToPrepTime(recipeInputDTO.getPrepTime()));
        recipe.setInstructions(convertToInstructionList(recipeInputDTO.getInstructions()));
        recipe.setCreatedByUser(user);
        recipe.setTags(convertToTag(recipeInputDTO.getTags()));
        recipe.setIngredients(convertToIngredient(recipeInputDTO.getIngredients()));
        recipe.setImageFile(recipe.getImageFile());
        return recipe;
    }

    public RecipeOutputDTO convertToRecipeOutputDTO(Recipe recipe) {
        RecipeOutputDTO recipeOutputDTO = new RecipeOutputDTO();
        recipeOutputDTO.setRecipeId(recipe.getRecipeId());
        recipeOutputDTO.setRecipeName(recipe.getRecipeName());
        recipeOutputDTO.setServings(recipe.getServings());
        recipeOutputDTO.setPrepTime(convertToPrepTimeDTO(recipe.getPrepTime()));
        recipeOutputDTO.setInstructions(convertToInstructionDTOList(recipe.getInstructions()));
        recipeOutputDTO.setCreatedByUser(recipe.getCreatedByUser().getUsername());
        recipeOutputDTO.setTags(convertToTagDTO(recipe.getTags()));
        recipeOutputDTO.setIngredients(convertToIngredientDTO(recipe.getIngredients()));
        recipeOutputDTO.setImageFile(recipe.getImageFile());
        return recipeOutputDTO;
    }

    public Recipe updateRecipe(Recipe recipe, RecipeInputDTO recipeInputDTO) {
        recipe.setRecipeName(recipeInputDTO.getRecipeName());
        recipe.setServings(recipeInputDTO.getServings());
        recipe.setPrepTime(convertToPrepTime(recipeInputDTO.getPrepTime()));
        recipe.setInstructions(convertToInstructionList(recipeInputDTO.getInstructions()));
        recipe.setCreatedByUser(recipe.getCreatedByUser());
        recipe.setTags(convertToTag(recipeInputDTO.getTags()));
        recipe.setIngredients(convertToIngredient(recipeInputDTO.getIngredients()));
        recipe.setImageFile(recipe.getImageFile());
        return recipe;
    }

    public List<Ingredient> convertToIngredient(List<IngredientDTO> ingredientDTOList) {
        List<Ingredient> ingredientList = new ArrayList<>();
        for (IngredientDTO ingredientDTO : ingredientDTOList) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(ingredientDTO.getName());
            ingredient.setUnit(ingredientDTO.getUnit());
            ingredient.setAmount(ingredientDTO.getAmount());
            ingredientList.add(ingredient);
        }
        return ingredientList;
    }

    public List<IngredientDTO> convertToIngredientDTO(List<Ingredient> ingredientList) {
        List<IngredientDTO> ingredientDTOList = new ArrayList<>();
        for (Ingredient ingredient : ingredientList) {
            IngredientDTO ingredientDTO = new IngredientDTO();
            ingredientDTO.setName(ingredient.getName());
            ingredientDTO.setUnit(ingredient.getUnit());
            ingredientDTO.setAmount(ingredient.getAmount());
            ingredientDTOList.add(ingredientDTO);
        }
        return ingredientDTOList;
    }

    public List<RecipeTag> convertToTag(List<RecipeTagDTO> tagDTO) {
        List<RecipeTag> tags = new ArrayList<>();

        for (RecipeTagDTO recipeTagDTO : tagDTO) {
            RecipeTag tag = new RecipeTag();
            tag.setTagName(recipeTagDTO.getTagName());
            tags.add(tag);
        }
        return tags;
    }

    public List<RecipeTagDTO> convertToTagDTO(List<RecipeTag> tag) {
        List<RecipeTagDTO> tagDTO = new ArrayList<>();

        for (RecipeTag recipeTag : tag) {
            RecipeTagDTO recipeTagDTO = new RecipeTagDTO();
            recipeTagDTO.setTagName(recipeTag.getTagName());
            tagDTO.add(recipeTagDTO);
        }
        return tagDTO;
    }

    public PrepTime convertToPrepTime(PrepTimeDTO prepTimeDTO) {
        PrepTime prepTime = new PrepTime();
        prepTime.setHour(prepTime.getHour());
        prepTime.setMinute(prepTime.getMinute());
        return prepTime;
    }

    public PrepTimeDTO convertToPrepTimeDTO(PrepTime prepTime) {
        PrepTimeDTO prepTimeDTO = new PrepTimeDTO();
        prepTimeDTO.setHour(prepTime.getHour());
        prepTimeDTO.setMinute(prepTime.getMinute());
        return prepTimeDTO;
    }

    public List<RecipeInstruction> convertToInstructionList(List<InstructionDTO> instructionDTOList) {
        List<RecipeInstruction> recipeInstructionList = new ArrayList<>();

        for (InstructionDTO recipeInstructionDTO : instructionDTOList) {
            RecipeInstruction recipeInstruction = new RecipeInstruction();
            recipeInstruction.setStep(recipeInstructionDTO.getStep());
            recipeInstruction.setDescription(recipeInstructionDTO.getInstruction());
            recipeInstructionList.add(recipeInstruction);
        }
        return recipeInstructionList;
    }

    public List<InstructionDTO> convertToInstructionDTOList(List<RecipeInstruction> recipeInstructionList) {
        List<InstructionDTO> instructionDTOList = new ArrayList<>();

        for (RecipeInstruction recipeInstruction : recipeInstructionList) {
            InstructionDTO instructionDTO = new InstructionDTO();
            instructionDTO.setStep(recipeInstruction.getStep());
            instructionDTO.setInstruction(recipeInstruction.getDescription());
            instructionDTOList.add(instructionDTO);
        }
        return instructionDTOList;
    }
}
