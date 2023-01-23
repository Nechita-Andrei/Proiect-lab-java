package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.*;
import proiect.service.*;

@RestController
@RequestMapping("/zbor")
@Slf4j
public class ZborController {

    @Autowired
    private ZborService zborService;

    @Autowired
    private PilotService pilotService;

    @Autowired
    private AvionService avionService;

    @Autowired
    private AeroportService aeroportService;

    @Autowired
    private CompanieAerianaService companieAerianaService;


    @GetMapping
    public ModelAndView getZboruri(){
        Iterable<Zbor> zboruri = zborService.getZboruri();
        ModelAndView modelAndView = new ModelAndView("zboruri");
        modelAndView.addObject("zboruri", zboruri);
        return modelAndView;

    }


    @RequestMapping("/new")
    public ModelAndView newZbor(){
        ModelAndView modelAndView=new ModelAndView("zborForm");
        modelAndView.addObject("zbor", new Zbor());
        modelAndView.addObject("avioane",avionService.getAvioane());
        modelAndView.addObject("piloti",pilotService.getPiloti());
        modelAndView.addObject("aeroporturi",aeroportService.getAeroporturi());
        modelAndView.addObject("companii_aeriane",companieAerianaService.getCompaniiAeriane());
        return modelAndView;
    }

    @PostMapping
    public ModelAndView addZbor(@ModelAttribute Zbor zbor) {
        Zbor zbor_res=zborService.addZbor(zbor);
        log.info("s-a adaugat cu succes un zbor: "+zbor_res.toString());
        return new ModelAndView("redirect:/zbor");
    }

    @RequestMapping("/delete/{id_zbor}")
    public ModelAndView deleteZbor(@PathVariable("id_zbor") int id_zbor) throws Exception {
        zborService.deleteZbor(id_zbor);
        return new ModelAndView("redirect:/zbor");
    }




}
