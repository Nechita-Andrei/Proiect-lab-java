package proiect.repository;

import proiect.domain.Aeroport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportRepo extends CrudRepository<Aeroport,Integer> {

}
