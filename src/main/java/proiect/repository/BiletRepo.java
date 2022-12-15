package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;
import proiect.domain.BiletId;

import java.util.Optional;

@Repository
public interface BiletRepo extends CrudRepository<Bilet,BiletId> {
}
