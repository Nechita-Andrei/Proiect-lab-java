package proiect.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Contact;
import proiect.service.ContactService;

import java.util.Set;

@RestController
@RequestMapping("/contact")
@Slf4j
public class ContactController {

    @Autowired
    private ContactService contactService;


    @PostMapping
    public ModelAndView addContact(@ModelAttribute Contact contact) {
        Contact con=contactService.addContact(contact);
        log.info("s-a adaugat cu succes un contact nou: "+con.toString());
        return new ModelAndView("redirect:/contact");
    }

    @GetMapping
    public ModelAndView getContacte() {
        Iterable<Contact> contacte = contactService.getContacte();
        ModelAndView modelAndView = new ModelAndView("contacte");
        modelAndView.addObject("contacte", contacte);
        return modelAndView;

    }

    @RequestMapping("/new")
    public ModelAndView newContact() {
        ModelAndView modelAndView = new ModelAndView("contactForm");
        modelAndView.addObject("contact", new Contact());
        return modelAndView;
    }
}
