package dev.rick.mandjesenpuutjesbackend2024.recipe.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class PrepTime {

    private int hour;
    private int minute;

}
