package dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListInputDTO {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;

    private String[] products;

    private String username;
}
