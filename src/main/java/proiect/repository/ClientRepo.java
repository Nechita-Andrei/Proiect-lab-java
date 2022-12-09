package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import proiect.domain.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Zbor;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends CrudRepository<Client,Integer> {

}
