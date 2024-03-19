package dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class ProductDTO {

    @Id
    @GeneratedValue
    private long productId;

    @Column(name = "name", nullable = false)
    private String productName;
}
