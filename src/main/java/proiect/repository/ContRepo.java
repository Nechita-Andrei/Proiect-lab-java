package proiect.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Cont;

import java.util.Optional;

@Repository
public interface ContRepo extends CrudRepository<Cont,Integer> {
    Optional<Cont> findByNumarCard(String numar_card);
}
