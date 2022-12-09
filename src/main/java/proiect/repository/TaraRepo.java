package proiect.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Tara;

@Repository
public interface TaraRepo extends CrudRepository<Tara, Integer> {
}
