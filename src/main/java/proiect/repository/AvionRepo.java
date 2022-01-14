package proiect.repository;

import proiect.domain.Avion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepo extends CrudRepository<Avion,Integer> {
}
