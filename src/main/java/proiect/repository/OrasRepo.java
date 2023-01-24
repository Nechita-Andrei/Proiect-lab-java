package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;
import proiect.domain.Oras;

import java.util.List;

@Repository
public interface OrasRepo extends CrudRepository<Oras, Integer> {

    List<Oras> findTop100ByOrderByIdOrasDesc();
}
