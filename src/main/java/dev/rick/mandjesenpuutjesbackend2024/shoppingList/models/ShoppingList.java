package dev.rick.mandjesenpuutjesbackend2024.shoppingList.models;

import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "shopping_lists")
public class ShoppingList {


    @Id
    @GeneratedValue
    private int id;

    @Column(name = "created")
    private LocalDate creationDate;

    @OneToMany
    private List<Product> products = new ArrayList<>();

    @ManyToOne
    private User user;
}
