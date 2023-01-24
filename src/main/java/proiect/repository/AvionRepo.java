package proiect.repository;

import proiect.domain.Avion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;

import java.util.List;

@Repository
public interface AvionRepo extends CrudRepository<Avion,Integer> {
    List<Avion> findTop100ByOrderByIdAvionDesc();
}
