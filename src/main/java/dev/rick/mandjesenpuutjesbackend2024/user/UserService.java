package dev.rick.mandjesenpuutjesbackend2024.user;

import dev.rick.mandjesenpuutjesbackend2024.authority.Authority;
import dev.rick.mandjesenpuutjesbackend2024.authority.AuthorityDTO;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.NameIsTakenException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.NotAuthorizedException;
import dev.rick.mandjesenpuutjesbackend2024.exceptions.RecordNotFoundException;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserInputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.dto.UserShortOutputDTO;
import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepo;
    private final UserConverter converter;

    public UserShortOutputDTO registerNewUser(UserInputDTO newUser) {

        User isUsernameTaken = findUserByUsername(newUser.getUsername());
        if (isUsernameTaken != null) {
            throw new NameIsTakenException(newUser.getUsername());
        } else {
            User createdUser = converter.convertToUser(newUser);
            userRepo.save(createdUser);
            return converter.convertToShortOutputDTO(createdUser);
        }
    }

    public UserOutputDTO addAuthorityToRegisteredUser(AuthorityDTO authorityDTO) {
        User registeredUser = findUserByUsername(authorityDTO.getUsername());
        if (registeredUser != null) {
            Authority createdAuthority = converter.convertToAuthority(authorityDTO);
            registeredUser.addAuthority(createdAuthority);
            userRepo.save(registeredUser);

            return converter.convertToUserOutput(registeredUser);
        } else {
            throw new RecordNotFoundException(authorityDTO.getUsername());
        }
    }

    public UserOutputDTO getUserById(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser != null) {
            return converter.convertToUserOutput(foundUser);
        } else {
            throw new RecordNotFoundException(username);
        }
    }

    public UserOutputDTO getUserDetails(String username) {
        User foundUser = findUserByUsername(username);
        if (foundUser != null) {
            return converter.convertToUserOutput(foundUser);
        } else {
            throw new RecordNotFoundException(username);
        }
    }

    public UserShortOutputDTO addAuthorityToExistingUser(String username, AuthorityDTO authorityDTO) {
        User existingUser = findUserByUsername(username);
        if (existingUser != null) {
            existingUser.addAuthority(converter.convertToAuthority(authorityDTO));
            userRepo.save(existingUser);

            return converter.convertToShortOutputDTO(existingUser);
        } else {
            throw new RecordNotFoundException(username);
        }
    }

    public boolean addAdminAuthorityAsAdmin(Principal principal, String username) {

        boolean foundAdmin = doesAuthorityContainAdmin(principal.getName());
        if (foundAdmin) {
            User foundUser = findUserByUsername(username);
            if (foundUser != null) {
                foundUser.addAuthority(new Authority(username, "ADMIN"));
                return true;
            } else {
                throw new RecordNotFoundException(username);
            }
        } else {
            throw new NotAuthorizedException();
        }
    }

    public boolean doesAuthorityContainAdmin(String username) {
        User foundUser = findUserByUsername(username);

        List<String> listOfAuthorities = new ArrayList<>();

        if (foundUser != null) {

            Set<Authority> loggedInUserAuthorities = foundUser.getAuthorities();

            for (Authority authority : loggedInUserAuthorities) {
                String authorityName = authority.getAuthority();
                listOfAuthorities.add(authorityName);
            }
        }

        return listOfAuthorities.contains("ADMIN");
    }


    public User findUserByUsername(String username) {
        Optional<User> optionalUser = userRepo.findById(username);
        return optionalUser.orElse(null);
    }

    public boolean deleteUserAsAdmin(Principal principal, String username) {

        boolean foundAdmin = doesAuthorityContainAdmin(principal.getName());
        if (foundAdmin) {
            User foundUser = findUserByUsername(username);
            if (foundUser != null) {
                userRepo.deleteById(username);
                return true;
            } else {
                throw new RecordNotFoundException(username);
            }
        } else {
            throw new NotAuthorizedException();
        }
    }
}
