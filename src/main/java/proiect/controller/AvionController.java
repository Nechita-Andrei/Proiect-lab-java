package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Avion;
import proiect.service.AvionService;
import proiect.service.CompanieAerianaService;

@RestController
@RequestMapping("/avion")
@Slf4j
public class AvionController {
    @Autowired
    private AvionService avionService;

    @Autowired
    private CompanieAerianaService companieAerianaService;


    @PostMapping
    public ModelAndView addAvion(@ModelAttribute Avion avion) {
        Avion pilot_res= avionService.addAvion(avion);
        log.info("s-a adaugat cu succes un avion nou: "+pilot_res.toString());
        return new ModelAndView("redirect:/avion");
    }

    @GetMapping
    public ModelAndView getAvioane() {
        Iterable<Avion> avioane = avionService.getAvioane();
        ModelAndView modelAndView = new ModelAndView("avioane");
        modelAndView.addObject("avioane", avioane);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_avion}")
    public ModelAndView deleteAvion(@PathVariable("id_avion") int id_avion) throws Exception {
        avionService.deleteAvion(id_avion);
        return new ModelAndView("redirect:/avion");
    }
    @RequestMapping("/new")
    public ModelAndView newAvion() {
        ModelAndView modelAndView = new ModelAndView("avionForm");
        modelAndView.addObject("companii", companieAerianaService.getCompaniiAeriane());
        modelAndView.addObject("avion", new Avion());
        return modelAndView;
    }

}
