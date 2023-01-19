package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Oras;

@Repository
public interface OrasRepo extends CrudRepository<Oras, Integer> {
}
