package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Contact;
import proiect.domain.TipBilet;
import proiect.service.TipBiletService;

@RestController
@RequestMapping("/tip")
@Slf4j
public class TipBiletController {

    @Autowired
    private TipBiletService tipBiletService;

    @PostMapping
    public ModelAndView addTipBilet(@ModelAttribute TipBilet tipBilet) {
        TipBilet tipBilet_res=tipBiletService.addTipBilet(tipBilet);
        log.info("s-a adaugat cu succes un tip bilet nou: "+tipBilet_res.toString());
        return new ModelAndView("redirect:/tip");
    }

    @GetMapping
    public ModelAndView getTipuriBilet() {
        Iterable<TipBilet> tipuriBilet = tipBiletService.getTipuriBilet();
        ModelAndView modelAndView = new ModelAndView("tipuriBilet");
        modelAndView.addObject("tipuri", tipuriBilet);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_tip_bilet}")
    public ModelAndView deleteTipBilet(@PathVariable("id_tip_bilet") int id_tip_bilet) throws Exception {
        tipBiletService.deleteTipBilet(id_tip_bilet);
        return new ModelAndView("redirect:/tip");
    }
    @RequestMapping("/new")
    public ModelAndView newTipBilet() {
        ModelAndView modelAndView = new ModelAndView("tipBiletForm");
        modelAndView.addObject("tip", new TipBilet());
        return modelAndView;
    }
}
