package proiect.repository;

import proiect.domain.Zbor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ZborRepo extends CrudRepository<Zbor,Integer> {
}
