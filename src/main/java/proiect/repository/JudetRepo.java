package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Judet;

@Repository
public interface JudetRepo extends CrudRepository<Judet, Integer> {
}
