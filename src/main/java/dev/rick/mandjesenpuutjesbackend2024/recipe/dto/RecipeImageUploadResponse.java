package dev.rick.mandjesenpuutjesbackend2024.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RecipeImageUploadResponse {

    String fileName;
    String contentType;
    String url;
}
