package dev.rick.mandjesenpuutjesbackend2024.recipe.models;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class RecipeTag {

    private String tagName;
}
