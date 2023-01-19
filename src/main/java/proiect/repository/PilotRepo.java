package proiect.repository;

import org.springframework.data.jpa.repository.Query;
import proiect.domain.Pilot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.PilotRaport;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PilotRepo extends CrudRepository<Pilot,Integer> {
    @Query(value = "select nume, prenume from dw.pilot", nativeQuery = true)
    List<PilotRaport> raport1();
}
