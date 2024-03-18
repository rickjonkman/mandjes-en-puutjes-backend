package dev.rick.mandjesenpuutjesbackend2024.user;


import dev.rick.mandjesenpuutjesbackend2024.authority.AuthorityDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.RegisterOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.utils.InputValidator;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;
    private final InputValidator validator;

    @PostMapping("/users/register")
    public ResponseEntity<?> registerNewUser(@Valid @RequestBody UserInputDTO newUser, BindingResult bindingResult) {

        RegisterOutputDTO registerOutputDTO;

        if (bindingResult.hasFieldErrors()) {
            String responseBody = validator.inputValidator(bindingResult);
            return ResponseEntity.badRequest().body(responseBody);
        } else {
            registerOutputDTO = userService.registerNewUser(newUser);
        }

        AuthorityDTO authorityDTO = new AuthorityDTO(registerOutputDTO.getUsername(), "USER");

        UserOutputDTO outputDTO = userService.addAuthorityToRegisteredUser(authorityDTO);

        return ResponseEntity.created(ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/api/v1/user/"+outputDTO.getUsername())
                        .build().toUri())
                .body(outputDTO);

    }
}
