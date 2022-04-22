package proiect.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import proiect.domain.Zbor;
import proiect.service.ZborService;

import java.util.Optional;

@Controller
@Slf4j
public class HomeController {


    @Autowired
    private ZborService zborService;

    @GetMapping
    public ModelAndView getZboruri(@RequestParam("page") Optional<Integer> page, @RequestParam(value = "sort", required = false) Optional<String> sort_column){
        ModelAndView modelAndView=new ModelAndView("zbor");
        int currentPage=page.orElse(1);
        int pageSize = 5;
        log.info("inainte este: "+sort_column);
        String column_to_sort=sort_column.orElse("idasc");
        log.info("se sorteaza dupa: "+column_to_sort);
        Page<Zbor> zborPage=zborService.findPaginated(PageRequest.of(currentPage-1,pageSize),column_to_sort);
        modelAndView.addObject("zboruri",zborPage);
        return modelAndView;
    }

    @GetMapping("/showLogInForm")
    public String showLogInForm(){ return "login"; }
    @GetMapping("/login-error")
    public String loginError() {
        return "login-error";
    }

    @GetMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }
}
