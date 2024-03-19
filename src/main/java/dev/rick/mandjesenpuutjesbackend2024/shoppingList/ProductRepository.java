package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
