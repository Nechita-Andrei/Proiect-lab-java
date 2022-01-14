package proiect.repository;

import proiect.domain.Pilot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PilotRepo extends CrudRepository<Pilot,Integer> {
}
