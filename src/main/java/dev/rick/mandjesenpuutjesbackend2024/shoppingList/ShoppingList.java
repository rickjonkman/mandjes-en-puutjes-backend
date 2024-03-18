package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import jakarta.persistence.ManyToOne;

public class ShoppingList {

    @ManyToOne
    private User user;
}
