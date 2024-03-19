package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.exceptions.NotAuthorizedException;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListOutputDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/shoppinglists")
@AllArgsConstructor
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping("/get-lists")
    public ResponseEntity<List<ShoppingListOutputDTO>> getShoppingLists(@RequestParam String username, Principal principal) {

        if (principal != null && username.equals(principal.getName())) {
            List<ShoppingListOutputDTO> shoppingLists = shoppingListService.getShoppingLists(username);
            return ResponseEntity.ok(shoppingLists);
        } else {
            throw new NotAuthorizedException();
        }
    }

    @PostMapping("/add-new")
    public ResponseEntity<ShoppingListOutputDTO> addNewShoppingList(
            @RequestParam String username, @RequestBody ShoppingListInputDTO shoppingListInputDTO, Principal principal) {

        if (principal != null && username.equals(principal.getName())) {
            ShoppingListOutputDTO shoppingList = shoppingListService.addNewShoppingList(shoppingListInputDTO, username);

            URI uri =
                    URI.create(
                            ServletUriComponentsBuilder
                                    .fromCurrentRequest()
                                    .path("/api/v1/shoppinglists/get-list/" + shoppingList.getId())
                                    .toUriString());

            return ResponseEntity.created(uri).body(shoppingList);
        } else {
            throw new NotAuthorizedException();
        }
    }
}
