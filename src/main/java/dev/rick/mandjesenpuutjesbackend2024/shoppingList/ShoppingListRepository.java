package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    @Query("SELECT s FROM ShoppingList s WHERE s.user.username = :username ORDER BY s.id DESC LIMIT 5")
    Optional<List<ShoppingList>> findShoppingListsByUser_Username(String username);
}
