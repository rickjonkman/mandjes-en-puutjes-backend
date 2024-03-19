package dev.rick.mandjesenpuutjesbackend2024.recipe.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Diet {

    private boolean meat;
    private boolean fish;
    private boolean vega;
    private boolean vegan;
}
