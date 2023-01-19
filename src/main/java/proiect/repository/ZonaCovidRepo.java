package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.ZonaCovid;


@Repository
public interface ZonaCovidRepo extends CrudRepository<ZonaCovid, Integer> {
}
