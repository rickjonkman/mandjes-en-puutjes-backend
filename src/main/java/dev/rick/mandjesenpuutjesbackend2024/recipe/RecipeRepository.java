package dev.rick.mandjesenpuutjesbackend2024.recipe;

import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Diet;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    Optional<List<Recipe>> findAllByCreatedByUser_Username(String username);

    Optional<List<Recipe>> findAllByDiet(Diet diet);

//    Optional<List<Recipe>> findAllByDiet_Meat(boolean meat);
//    Optional<List<Recipe>> findAllByDiet_Fish(boolean fish);
//    Optional<List<Recipe>> findAllByDiet_Vega(boolean vega);
//    Optional<List<Recipe>> findAllByDiet_Vegan(boolean vegan);
}
