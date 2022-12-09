package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Judet;
import proiect.service.JudetService;
import proiect.service.TaraService;

@RestController
@RequestMapping("/judet")
@Slf4j
public class JudetController {
    @Autowired
    private JudetService judetService;

    @Autowired
    private TaraService taraService;

    @PostMapping
    public ModelAndView addJudet(@ModelAttribute Judet judet) {
        Judet judet_res=judetService.addJudet(judet);
        log.info("s-a adaugat cu succes un judet: "+judet_res.toString());
        return new ModelAndView("redirect:/judet");
    }

    @GetMapping
    public ModelAndView getJudete() {
        Iterable<Judet> judete = judetService.getJudete();
        ModelAndView modelAndView = new ModelAndView("judete");
        modelAndView.addObject("judete", judete);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_judet}")
    public ModelAndView deleteJudet(@PathVariable("id_judet") int id_judet) throws Exception {
        judetService.deleteJudet(id_judet);
        return new ModelAndView("redirect:/judet");
    }
    @RequestMapping("/new")
    public ModelAndView newJudet() {
        ModelAndView modelAndView = new ModelAndView("judetForm");
        modelAndView.addObject("judet", new Judet());
        modelAndView.addObject("tari", taraService.getTari());
        return modelAndView;
    }
}
