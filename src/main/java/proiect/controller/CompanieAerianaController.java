package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.CompanieAeriana;
import proiect.service.CompanieAerianaService;
import proiect.service.ContactService;

@RestController
@RequestMapping("/companie")
@Slf4j
public class CompanieAerianaController {

    @Autowired
    private CompanieAerianaService companieAerianaService;

    @Autowired
    private ContactService contactService;


    @PostMapping
    public ModelAndView addCompanieAeriana(@ModelAttribute CompanieAeriana companieAeriana) {
        CompanieAeriana companie= companieAerianaService.addCompanieAeriana(companieAeriana);
        log.info("s-a adaugat cu succes o companie aeriana noua: "+companie.toString());
        return new ModelAndView("redirect:/companie");
    }

    @GetMapping
    public ModelAndView getCompanii() {
        Iterable<CompanieAeriana> companii = companieAerianaService.getCompaniiAeriana();
        ModelAndView modelAndView = new ModelAndView("companii");
        modelAndView.addObject("companii", companii);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_companie_aeriana}")
    public ModelAndView deleteCompanieAeriana(@PathVariable("id_companie_aeriana") int id_companie_aeriana) throws Exception {
        companieAerianaService.deleteCompanieAeriana(id_companie_aeriana);
        return new ModelAndView("redirect:/companie");
    }
    @RequestMapping("/new")
    public ModelAndView newCompanie() {
        ModelAndView modelAndView = new ModelAndView("companieForm");
        modelAndView.addObject("companie", new CompanieAeriana());
        modelAndView.addObject("contacte", contactService.getContacte());
        return modelAndView;
    }
}
