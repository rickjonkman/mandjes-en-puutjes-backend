package dev.rick.mandjesenpuutjesbackend2024.recipe.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RecipeOutputDTO {

    private long recipeId;
    private String recipeName;
    private int servings;
    private PrepTimeDTO prepTime;
    private String imageFile;

    private List<RecipeTagDTO> tags = new ArrayList<>();
    private List<IngredientDTO> ingredients = new ArrayList<>();
    private List<InstructionDTO> instructions = new ArrayList<>();

    private boolean meat;
    private boolean fish;
    private boolean vega;
    private boolean vegan;

    private String createdByUser;
    private int savedByUsers;
}
