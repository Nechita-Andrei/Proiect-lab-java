package proiect.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Adresa;
import proiect.service.AdresaService;
import proiect.service.OrasService;

@RestController
@RequestMapping("/adresa")
@Slf4j
public class AdresaController {
    @Autowired
    private AdresaService adresaService;

    @Autowired
    private OrasService orasService;



    @PostMapping
    public ModelAndView addAdresa(@ModelAttribute Adresa adresa) {
        Adresa adresa_noua = adresaService.addAdresa(adresa);
        log.info("s-a adaugat cu succes o adresa noua: " + adresa_noua.toString());
        return new ModelAndView("redirect:/adresa");
    }

    @GetMapping
    public ModelAndView getAdrese() {
        Iterable<Adresa> adrese = adresaService.getAdrese();
        ModelAndView modelAndView = new ModelAndView("adrese");
        modelAndView.addObject("adrese", adrese);
        return modelAndView;
    }

    @RequestMapping("/delete/{id_adresa}")
    public ModelAndView deleteAdresa(@PathVariable("id_adresa") int id_adresa) throws Exception {
        adresaService.deleteAdresa(id_adresa);
        return new ModelAndView("redirect:/adresa");
    }
    @RequestMapping("/new")
    public ModelAndView newAdresa() {
        ModelAndView modelAndView = new ModelAndView("adresaForm");
        modelAndView.addObject("adresa", new Adresa());
        modelAndView.addObject("orase", orasService.getOrase());
        return modelAndView;
    }

}
