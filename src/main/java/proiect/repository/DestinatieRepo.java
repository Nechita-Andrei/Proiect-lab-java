package proiect.repository;

import proiect.domain.Destinatie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DestinatieRepo extends CrudRepository<Destinatie, Integer> {
    Optional<Destinatie> findByLocalitate(String localitate);
}
