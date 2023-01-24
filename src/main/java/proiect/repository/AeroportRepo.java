package proiect.repository;

import proiect.domain.Aeroport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;

import java.util.List;

@Repository
public interface AeroportRepo extends CrudRepository<Aeroport,Integer> {
    List<Aeroport> findTop100ByOrderByIdAeroportDesc();
}
