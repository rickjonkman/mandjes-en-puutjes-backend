package dev.rick.mandjesenpuutjesbackend2024.recipe.models;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {

    private int amount;
    private String unit;
    private String name;

}
