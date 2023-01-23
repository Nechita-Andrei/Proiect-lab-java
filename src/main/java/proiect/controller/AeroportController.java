package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Aeroport;
import proiect.service.AdresaService;
import proiect.service.AeroportService;
import proiect.service.CompanieAerianaService;

@RestController
@RequestMapping("/aeroport")
@Slf4j
public class AeroportController {
    @Autowired
    private AeroportService aeroportService;

    @Autowired
    private AdresaService adresaService;


    @PostMapping
    public ModelAndView addAeroport(@ModelAttribute Aeroport aeroport) {
        Aeroport aeroport_nou = aeroportService.addAeroport(aeroport);
        log.info("s-a adaugat cu succes un aeroport nou: " + aeroport_nou.toString());
        return new ModelAndView("redirect:/aeroport");
    }

    @GetMapping
    public ModelAndView getAeroporturi() {
        Iterable<Aeroport> aeroporturi = aeroportService.getAeroporturi();
        ModelAndView modelAndView = new ModelAndView("aeroporturi");
        modelAndView.addObject("aeroporturi", aeroporturi);
        return modelAndView;
    }

    @RequestMapping("/delete/{id_aeroport}")
    public ModelAndView deleteAeroport(@PathVariable("id_aeroport") int id_aeroport) throws Exception {
        aeroportService.deleteAeroport(id_aeroport);
        return new ModelAndView("redirect:/aeroport");
    }
    @RequestMapping("/new")
    public ModelAndView newAeroport() {
        ModelAndView modelAndView = new ModelAndView("aeroportForm");
        modelAndView.addObject("adrese", adresaService.getAdrese());
        modelAndView.addObject("aeroport", new Aeroport());
        return modelAndView;
    }

}
