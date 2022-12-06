package proiect.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import proiect.domain.Contact;

@Repository
public interface ContactRepo extends CrudRepository<Contact, Integer> {

}
