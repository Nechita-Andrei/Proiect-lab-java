package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Bilet;
import proiect.domain.Contact;

import java.util.List;

@Repository
public interface ContactRepo extends CrudRepository<Contact, Integer> {
    List<Contact> findTop100ByOrderByIdContactDesc();

}
