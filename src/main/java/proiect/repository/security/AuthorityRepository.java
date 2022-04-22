package proiect.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import proiect.domain.Security.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
