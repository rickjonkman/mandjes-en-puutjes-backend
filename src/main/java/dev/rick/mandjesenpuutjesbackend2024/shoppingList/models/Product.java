package dev.rick.mandjesenpuutjesbackend2024.shoppingList.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private long productId;

    @Column(name = "name", nullable = false)
    private String productName;

    @ManyToOne
    private ShoppingList shoppingList;
}
