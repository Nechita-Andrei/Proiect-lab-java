package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.TipBilet;

@Repository
public interface TipBiletRepo extends CrudRepository<TipBilet, Integer> {
}
