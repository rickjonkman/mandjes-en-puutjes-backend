package dev.rick.mandjesenpuutjesbackend2024.user;

import dev.rick.mandjesenpuutjesbackend2024.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
