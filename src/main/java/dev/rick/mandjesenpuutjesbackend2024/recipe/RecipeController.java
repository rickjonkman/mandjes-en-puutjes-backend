package dev.rick.mandjesenpuutjesbackend2024.recipe;


import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.dto.RecipeOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Diet;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    private final RecipeService recipeService;


    @PostMapping("/add-new")
    public ResponseEntity<RecipeOutputDTO> addNewRecipe(Principal principal, @RequestBody RecipeInputDTO inputDTO) {
        RecipeOutputDTO outputDTO = recipeService.addRecipe(inputDTO, principal.getName());

        URI uri = URI.create(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/v1/recipes/"+outputDTO.getRecipeId())
                        .toUriString());
        return ResponseEntity.created(uri).body(outputDTO);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<RecipeOutputDTO>> getAllRecipes() {
        List<RecipeOutputDTO> outputDTOList = recipeService.getAllRecipes();
        return ResponseEntity.ok(outputDTOList);
    }

    @GetMapping("/get-recipe/{recipeId}")
    public ResponseEntity<RecipeOutputDTO> getRecipe(@PathVariable Long recipeId) {
        RecipeOutputDTO outputDTO = recipeService.getRecipeById(recipeId);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping("/get-recipes")
    public ResponseEntity<List<RecipeOutputDTO>> getRecipesByCreator(@RequestParam String username) {
        List<RecipeOutputDTO> outputList = recipeService.getRecipesByCreator(username);
        return ResponseEntity.ok(outputList);
    }

    @PutMapping("/auth/update-recipe/{recipeId}")
    public ResponseEntity<RecipeOutputDTO> updateRecipe(
            @PathVariable Long recipeId, @RequestBody RecipeInputDTO inputDTO, Principal principal) {
        RecipeOutputDTO outputDTO = recipeService.updateRecipeById(recipeId, inputDTO, principal);
        return ResponseEntity.ok(outputDTO);
    }

    @DeleteMapping("/auth/delete-recipe/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable Long recipeId, Principal principal) {
        recipeService.deleteRecipeById(recipeId, principal);
        return ResponseEntity.noContent().build();
    }

}
