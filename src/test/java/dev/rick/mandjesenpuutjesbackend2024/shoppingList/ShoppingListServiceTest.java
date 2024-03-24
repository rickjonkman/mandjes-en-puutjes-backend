package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.exceptions.NothingFoundForUserException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ProductDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ProductInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.Product;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.ShoppingList;
import dev.rick.mandjesenpuutjesbackend2024.user.UserRepository;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingListServiceTest {

    @Mock
    private ShoppingListRepository listRepository;

    @Mock
    private ShoppingListConverter listConverter;

    @Mock
    private UserRepository userRepository;


    @InjectMocks
    private ShoppingListService listService;

    @Test
    void shouldGetShoppingLists() {

        String username = "sandra@novi.nl";

        List<ShoppingList> shoppingLists = new ArrayList<>();
        shoppingLists.add(new ShoppingList());
        shoppingLists.add(new ShoppingList());

        when(listRepository.findShoppingListsByUser_Username(username)).thenReturn(Optional.of(shoppingLists));
        when(listConverter.convertToDTO(shoppingLists.get(0))).thenReturn(new ShoppingListOutputDTO());
        when(listConverter.convertToDTO(shoppingLists.get(1))).thenReturn(new ShoppingListOutputDTO());

        List<ShoppingListOutputDTO> output = listService.getShoppingLists(username);

        assertThat(output).isNotNull();
        assertThat(output.size()).isEqualTo(2);
    }

    @Test
    void shouldAddNewShoppingList() {
        LocalDate date = LocalDate.of(2024, 3, 12);
        String username = "hans@novi.nl";

        User listOwner = new User();
        listOwner.setUsername(username);

        String[] productArray = { "Melk", "Walnoten", "Bananen" };

        ShoppingListInputDTO inputDTO = new ShoppingListInputDTO();
        inputDTO.setCreationDate(date);
        inputDTO.setUsername(username);
        inputDTO.setProducts(productArray);

        ShoppingList newList = new ShoppingList();
        newList.setUser(listOwner);

        ShoppingListOutputDTO outputDTO = new ShoppingListOutputDTO();
        outputDTO.setCreationDate(date);
        outputDTO.setUsername(username);
        outputDTO.setProducts(productArray);

        when(userRepository.findById(username)).thenReturn(Optional.of(listOwner));
        when(listConverter.createNewShoppingList(inputDTO, listOwner)).thenReturn(newList);
        when(listRepository.save(newList)).thenReturn(newList);
        when(listConverter.convertToDTO(newList)).thenReturn(outputDTO);

        ShoppingListOutputDTO output = listService.addNewShoppingList(inputDTO, username);

        assertThat(output).isNotNull();
        assertThat(output.getUsername()).isEqualTo(username);
    }

    @Test
    void shouldThrowNothingFoundForUserException() {
        String username = "eelco@novi.nl";

        ShoppingListInputDTO inputDTO = new ShoppingListInputDTO();
        inputDTO.setUsername(username);

        when(listRepository.findShoppingListsByUser_Username(username)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> listService.getShoppingLists(username))
                .isInstanceOf(NothingFoundForUserException.class);
    }

    @Test
    void shouldThrowRecordNotFoundException() {
        String username = "annemarie@novi.nl";

        ShoppingListInputDTO inputDTO = new ShoppingListInputDTO();
        inputDTO.setUsername(username);

        when(userRepository.findById(username)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> listService.addNewShoppingList(inputDTO, username))
                .isInstanceOf(RecordNotFoundException.class);
    }


}