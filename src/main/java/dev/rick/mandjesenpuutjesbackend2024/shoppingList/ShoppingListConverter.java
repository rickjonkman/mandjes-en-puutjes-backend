package dev.rick.mandjesenpuutjesbackend2024.shoppingList;

import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.dto.ShoppingListOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.Product;
import dev.rick.mandjesenpuutjesbackend2024.shoppingList.models.ShoppingList;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListConverter {

    private final ProductRepository productRepository;

    public ShoppingListConverter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public List<ShoppingListOutputDTO> convertToListDTO(List<ShoppingList> shoppingLists) {
        List<ShoppingListOutputDTO> outputDTOList = new ArrayList<>();

        for (ShoppingList list : shoppingLists) {
            ShoppingListOutputDTO singleList = convertToDTO(list);
            outputDTOList.add(singleList);
        }

        return outputDTOList;
    }


    public ShoppingListOutputDTO convertToDTO(ShoppingList shoppingList) {
        ShoppingListOutputDTO outputDTO = new ShoppingListOutputDTO();
        outputDTO.setId(shoppingList.getId());
        outputDTO.setCreationDate(shoppingList.getCreationDate());
        outputDTO.setUsername(shoppingList.getUser().getUsername());
        outputDTO.setProducts(convertToProductArray(shoppingList.getProducts()));
        return outputDTO;
    }


    public ShoppingList createNewShoppingList(ShoppingListInputDTO shoppingListInputDTO, User user) {
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setUser(user);
        shoppingList.setCreationDate(shoppingListInputDTO.getCreationDate());
        shoppingList.setProducts(convertNameToProduct(shoppingListInputDTO.getProducts()));
        return shoppingList;
    }

    public List<Product> convertNameToProduct(String[] productArray) {
        List<Product> products = new ArrayList<>();

        for (String productName : productArray) {
            Product newProduct = new Product();
            newProduct.setProductName(productName);
            productRepository.save(newProduct);

            products.add(newProduct);
        }

        return products;
    }


    public String[] convertToProductArray(List<Product> productList) {
        String[] productArray = new String[productList.size()];

        List<String> productNames = new ArrayList<>();
        for (Product product : productList) {
            String productName = product.getProductName();
            productNames.add(productName);
        }

        productNames.toArray(productArray);
        return productArray;
    }


}
