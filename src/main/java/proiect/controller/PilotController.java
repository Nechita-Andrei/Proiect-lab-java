package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Pilot;
import proiect.service.CompanieAerianaService;
import proiect.service.PilotService;

@RestController
@RequestMapping("/pilot")
@Slf4j
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @Autowired
    private CompanieAerianaService companieAerianaService;


    @PostMapping
    public ModelAndView addPilot(@ModelAttribute Pilot pilot) {
        Pilot pilot_res= pilotService.addPilot(pilot);
        log.info("s-a adaugat cu succes un pilot nou: "+pilot_res.toString());
        return new ModelAndView("redirect:/pilot");
    }

    @GetMapping
    public ModelAndView getPiloti() {
        Iterable<Pilot> piloti = pilotService.getPiloti();
        ModelAndView modelAndView = new ModelAndView("piloti");
        modelAndView.addObject("piloti", piloti);
        modelAndView.addObject("ceva", pilotService.raport1());
        return modelAndView;

    }

    @RequestMapping("/delete/{id_pilot}")
    public ModelAndView deletePilot(@PathVariable("id_pilot") int id_pilot) throws Exception {
        pilotService.deletePilot(id_pilot);
        return new ModelAndView("redirect:/pilot");
    }
    @RequestMapping("/new")
    public ModelAndView newPilot() {
        ModelAndView modelAndView = new ModelAndView("pilotForm");
        modelAndView.addObject("companii", companieAerianaService.getCompaniiAeriane());
        modelAndView.addObject("pilot", new Pilot());
        return modelAndView;
    }
}
