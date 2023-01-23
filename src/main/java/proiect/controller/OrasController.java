package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Oras;
import proiect.service.JudetService;
import proiect.service.OrasService;
import proiect.service.ZonaCovidService;


@RestController
@RequestMapping("/oras")
@Slf4j
public class OrasController {

    @Autowired
    private OrasService orasService;

    @Autowired
    private JudetService judetService;

    @Autowired
    private ZonaCovidService zonaCovidService;

    @PostMapping
    public ModelAndView addOras(@ModelAttribute Oras oras) {
        Oras oras_res=orasService.addOras(oras);
        log.info("s-a adaugat cu succes un oras: "+oras_res.toString());
        return new ModelAndView("redirect:/oras");
    }

    @GetMapping
    public ModelAndView getOrase() {
        Iterable<Oras> orase = orasService.getOrase();
        ModelAndView modelAndView = new ModelAndView("orase");
        modelAndView.addObject("orase", orase);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_oras}")
    public ModelAndView deleteOras(@PathVariable("id_oras") int id_oras) throws Exception {
        orasService.deleteOras(id_oras);
        return new ModelAndView("redirect:/oras");
    }

    @RequestMapping("/new")
    public ModelAndView newOras() {
        ModelAndView modelAndView = new ModelAndView("orasForm");
        modelAndView.addObject("oras", new Oras());
        modelAndView.addObject("judete", judetService.getJudete());
        modelAndView.addObject("zoneCovid", zonaCovidService.getZoneCovid());
        return modelAndView;
    }
}
