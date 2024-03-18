package dev.rick.mandjesenpuutjesbackend2024.user;


import dev.rick.mandjesenpuutjesbackend2024.authority.AuthorityDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserShortOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.utils.InputValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final InputValidator validator;

    @PostMapping("/users/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserInputDTO newUser, BindingResult bindingResult) {

        UserShortOutputDTO userShortOutputDTO;

        if (bindingResult.hasFieldErrors()) {
            String responseBody = validator.inputValidator(bindingResult);
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            userShortOutputDTO = userService.registerNewUser(newUser);
        }

        AuthorityDTO authorityDTO = new AuthorityDTO(userShortOutputDTO.getUsername(), "USER");

        UserOutputDTO outputDTO = userService.addAuthorityToRegisteredUser(authorityDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/v1/user/"+outputDTO.getUsername())
                        .build().toUri())
                .body(outputDTO);

    }

    @GetMapping("/user/get")
    public ResponseEntity<UserOutputDTO> getUserById(@RequestParam(name = "username") String username) {
        UserOutputDTO outputDTO = userService.getUserById(username);
        return ResponseEntity.ok(outputDTO);
    }

    @GetMapping("/user/get-user-details")
    public ResponseEntity<UserOutputDTO> getUserDetails(@RequestParam(name = "username") String username) {
        UserOutputDTO outputDTO = userService.getUserDetails(username);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/user/add-user-authority")
    public ResponseEntity<UserShortOutputDTO> addUserAuthority(@RequestParam(name = "username") String username, @RequestBody AuthorityDTO authorityDTO) {
        UserShortOutputDTO outputDTO = userService.addAuthorityToExistingUser(username, authorityDTO);
        return ResponseEntity.ok(outputDTO);
    }

    @PutMapping("/admin/add-admin-authority")
    public ResponseEntity<String> addAdminAuthorityAsAdmin(Principal principal, @RequestParam(name = "username") String username) {
        String confirmOutput = "Authority added to user: "+username;

        boolean confirmation = userService.addAdminAuthorityAsAdmin(principal, username);
        if (confirmation) {
            return ResponseEntity.ok(confirmOutput);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("/admin/delete-user")
    public ResponseEntity<String> deleteUserAsAdmin(Principal principal, @RequestParam(name = "username") String username) {
        String confirmOutput = "User succesfully deleted: "+username;

        boolean confirmation = userService.deleteUserAsAdmin(principal, username);
        if (confirmation) {
            return ResponseEntity.ok(confirmOutput);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
