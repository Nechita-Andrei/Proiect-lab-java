package proiect.repository;

import proiect.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends CrudRepository<Client,Integer> {
    Optional<Client> findByEmail(String email);
}
