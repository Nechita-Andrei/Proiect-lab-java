package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import proiect.domain.Adresa;
import proiect.domain.Bilet;

import java.util.List;

public interface AdresaRepo extends CrudRepository<Adresa, Integer> {

    List<Adresa> findTop100ByOrderByIdAdresaDesc();
}
