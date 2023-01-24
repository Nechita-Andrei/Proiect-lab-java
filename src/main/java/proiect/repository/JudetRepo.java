package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;
import proiect.domain.Judet;

import java.util.List;

@Repository
public interface JudetRepo extends CrudRepository<Judet, Integer> {
    List<Judet> findTop100ByOrderByIdJudetDesc();
}
