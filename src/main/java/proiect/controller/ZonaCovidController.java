package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.ZonaCovid;
import proiect.service.ZonaCovidService;

@RestController
@RequestMapping("/zonaCovid")
@Slf4j
public class ZonaCovidController {

    @Autowired
    private ZonaCovidService zonaCovidService;

    @PostMapping
    public ModelAndView addZonaCovid(@ModelAttribute ZonaCovid zonaCovid) {
        ZonaCovid zonaCovid_res=zonaCovidService.addZonaCovid(zonaCovid);
        log.info("s-a adaugat cu succes o zona covid: "+zonaCovid_res.toString());
        return new ModelAndView("redirect:/zona covid");
    }

    @GetMapping
    public ModelAndView getZoneCovid() {
        Iterable<ZonaCovid> zoneCovid = zonaCovidService.getZoneCovid();
        ModelAndView modelAndView = new ModelAndView("zone covid");
        modelAndView.addObject("zone covid", zoneCovid);
        return modelAndView;

    }

    @RequestMapping("/delete/{id_zona_covid}")
    public ModelAndView deleteZonaCovid(@PathVariable("id_zona_covid") int id_zona_covid) throws Exception {
        zonaCovidService.deleteZonaCovid(id_zona_covid);
        return new ModelAndView("redirect:/zona covid");
    }
    @RequestMapping("/new")
    public ModelAndView newZonaCovid() {
        ModelAndView modelAndView = new ModelAndView("zonaCovidForm");
        modelAndView.addObject("zona covid", new ZonaCovid());
        return modelAndView;
    }

}
