package proiect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proiect.domain.Contact;
import proiect.repository.ContactRepo;

import java.util.Set;

@Service
public class ContactService {


    @Autowired
    private ContactRepo contactRepo;

    public Contact addContact(Contact contact){
       return contactRepo.save(contact);
    }

    public Iterable<Contact> getContacte() {
        return contactRepo.findAll();
    }
}
