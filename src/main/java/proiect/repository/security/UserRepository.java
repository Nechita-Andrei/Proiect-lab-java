package proiect.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.domain.Security.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
