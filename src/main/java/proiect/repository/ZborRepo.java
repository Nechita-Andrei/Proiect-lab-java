package proiect.repository;

import org.springframework.data.domain.Sort;
import proiect.domain.Zbor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;


@Repository
public interface ZborRepo extends CrudRepository<Zbor,Integer> {
        Iterable<Zbor> findAll(Sort sort);

}
