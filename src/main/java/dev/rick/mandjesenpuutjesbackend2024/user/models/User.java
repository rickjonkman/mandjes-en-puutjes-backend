package dev.rick.mandjesenpuutjesbackend2024.user.models;

import dev.rick.mandjesenpuutjesbackend2024.authority.Authority;
import dev.rick.mandjesenpuutjesbackend2024.recipe.models.Recipe;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.ShoppingList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String password;
    private String firstname;
    private boolean enabled;

    @Embedded
    private UserPreferences preferences;

    @OneToMany(
            targetEntity = Authority.class,
            mappedBy = "username",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true)
    private Set<Authority> authorities = new HashSet<>();

//    @OneToMany(mappedBy = "user")
//    private List<ShoppingList> shoppingLists = new ArrayList<>();

    @ManyToMany
    private List<Recipe> savedRecipes;

    @OneToMany(mappedBy = "createdByUser")
    private List<Recipe> createdRecipes;



    public void addAuthority(Authority authority) {
        this.authorities.add(authority);
    }
}