package dev.rick.mandjesenpuutjesbackend2024.recipe.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RecipeOutputDTO {

    private long recipeId;
    private String recipeName;
    private int servings;
    private PrepTimeDTO prepTime;
    private String imageFile;
    private List<RecipeTagDTO> tags = new ArrayList<>();
    private List<IngredientDTO> ingredients = new ArrayList<>();
    private List<InstructionDTO> instructions = new ArrayList<>();
    private String createdByUser;
    private int savedByUsers;
}
