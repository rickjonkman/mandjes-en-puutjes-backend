package dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListOutputDTO {

    private int id;
    private LocalDate creationDate;
    private String[] products;
    private String username;
}
