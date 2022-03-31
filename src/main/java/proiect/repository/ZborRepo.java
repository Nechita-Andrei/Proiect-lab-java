package proiect.repository;

import proiect.domain.Zbor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface ZborRepo extends CrudRepository<Zbor,Integer> {

}
