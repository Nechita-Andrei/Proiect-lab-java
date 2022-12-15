package proiect.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import proiect.domain.Bilet;
import proiect.domain.Client;
import proiect.domain.Zbor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import proiect.service.*;

import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/bilet")
@Slf4j
public class BiletController {

    @Autowired
    private BiletService biletService;
    @Autowired
    private TipBiletService tipBiletService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ZborService zborService;

    @PostMapping
    public ModelAndView addBilet(@ModelAttribute Bilet bilet) {
        Bilet bilet_nou = biletService.addBilet(bilet);
        log.info("s-a adaugat cu succes un bilet nou: " + bilet_nou.toString());
        return new ModelAndView("redirect:/bilet");
    }

    @GetMapping
    public ModelAndView getBileturi() {
        Iterable<Bilet> bilete = biletService.getBilete();
        ModelAndView modelAndView = new ModelAndView("bilete");
        modelAndView.addObject("bilete", bilete);
        return modelAndView;
    }

    @RequestMapping("/delete/{id_client}&{id_zbor}")
    public ModelAndView deleteBilet(@PathVariable("id_client") int id_client, @PathVariable("id_zbor") int id_zbor) throws Exception {
        biletService.deleteBilet(id_client, id_zbor);
        return new ModelAndView("redirect:/bilet");
    }
    @RequestMapping("/new")
    public ModelAndView newBilet() {
        ModelAndView modelAndView = new ModelAndView("biletForm");
        modelAndView.addObject("tipuri_bilet", tipBiletService.getTipuriBilet());
        modelAndView.addObject("clienti", clientService.getClienti());
        modelAndView.addObject("zboruri", zborService.getAllZboruri());
        modelAndView.addObject("bilet", new Bilet());
        return modelAndView;
    }


    @GetMapping(path = "/pasageri/{zbor_id}")
    public ModelAndView getPasageri(@PathVariable("zbor_id") int zborId){
            Set<Client> pasageri= biletService.getPasageri(zborId);
            ModelAndView modelAndView=new ModelAndView("pasageri");
            modelAndView.addObject("pasageri",pasageri);
            log.info("s-au afisat pasagerii pentru zborul: "+zborId);
            return modelAndView;
    }






    @RequestMapping(path = "/filtrare")
    public ModelAndView filtreazaZboruri(@RequestParam("page") Optional<Integer> page,@RequestParam("filtrare_plecare") String plecare, @RequestParam ("filtrare_localitate") String localitate){

        if(plecare=="" || localitate==""){
            return new ModelAndView("redirect:/zbor");
        }
        ModelAndView modelAndView=new ModelAndView("zbor");
        int currentPage=page.orElse(1);
        int pageSize = 5;

        Page<Zbor> zborPage=biletService.findPaginatedFiltered(PageRequest.of(currentPage-1,pageSize),Date.valueOf(plecare),localitate);
        modelAndView.addObject("zboruri",zborPage);
        return modelAndView;
    }






}
