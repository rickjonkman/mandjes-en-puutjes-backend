package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.exceptions.NothingFoundForUserException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.ShoppingList;
import dev.rick.mandjesenpuutjesbackend2024.user.UserRepository;
import dev.rick.mandjesenpuutjesbackend2024.user.UserService;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepo;
    private final ShoppingListConverter converter;
    private final UserRepository userRepo;

    public List<ShoppingListOutputDTO> getShoppingLists(String username) {
        List<ShoppingList> foundLists = findShoppingListsByUsername(username);

        List<ShoppingListOutputDTO> outputLists = new ArrayList<>();
        for (ShoppingList shoppingList : foundLists) {
            ShoppingListOutputDTO singleDTOList = converter.convertToDTO(shoppingList);
            outputLists.add(singleDTOList);
        }
        return outputLists;
    }

    public ShoppingListOutputDTO addNewShoppingList(ShoppingListInputDTO shoppingListInputDTO, String username) {

        User foundUser = findUserByUsername(username);
        if (foundUser == null) {
            throw new RecordNotFoundException(username);
        } else {
            ShoppingList newList = converter.createNewShoppingList(shoppingListInputDTO, foundUser);
            shoppingListRepo.save(newList);

            return converter.convertToDTO(newList);
        }
    }



    public List<ShoppingList> findShoppingListsByUsername(String username) {
        Optional<List<ShoppingList>> optionalShoppingLists = shoppingListRepo.findShoppingListsByUser_Username(username);
        return optionalShoppingLists.orElse(null);
    }

    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepo.findById(username);
        return optionalUser.orElse(null);
    }
}
