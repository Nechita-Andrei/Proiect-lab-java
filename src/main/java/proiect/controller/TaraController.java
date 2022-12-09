package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Tara;
import proiect.service.TaraService;

@RestController
@RequestMapping("/tara")
@Slf4j
public class TaraController {
    @Autowired
    private TaraService taraService;

    @PostMapping
    public ModelAndView addTara(@ModelAttribute Tara tara) {
        Tara tara_res=taraService.addTara(tara);
        log.info("s-a adaugat cu succes o tara: "+tara_res.toString());
        return new ModelAndView("redirect:/tara");
    }

    @GetMapping
    public ModelAndView getTari() {
        Iterable<Tara> tari = taraService.getTari();
        ModelAndView modelAndView = new ModelAndView("tari");
        modelAndView.addObject("tari", tari);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_tara}")
    public ModelAndView deleteTara(@PathVariable("id_tara") int id_tara) throws Exception {
        taraService.deleteTara(id_tara);
        return new ModelAndView("redirect:/tara");
    }
    @RequestMapping("/new")
    public ModelAndView newTara() {
        ModelAndView modelAndView = new ModelAndView("taraForm");
        modelAndView.addObject("tara", new Tara());
        return modelAndView;
    }

}
