package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Client;
import proiect.domain.Contact;
import proiect.repository.ContactRepo;

import java.util.Optional;
import java.util.Set;

@Service
public class ContactService {


    @Autowired
    private ContactRepo contactRepo;

    public Contact addContact(Contact contact){
       return contactRepo.save(contact);
    }
    public void deleteContact(Integer contact_id) throws Exception {
        Optional<Contact> contact=contactRepo.findById(contact_id);
        if(!contact.isPresent()) {
           throw new Exception("no contact found");
        }
        contactRepo.delete(contact.get());
    }

    public Iterable<Contact> getContacte() {
        return contactRepo.findAll();
    }
}
