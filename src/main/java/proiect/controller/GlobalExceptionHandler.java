package proiect.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import proiect.service.ClientException;
import proiect.service.ZborException;

@ControllerAdvice
public class GlobalExceptionHandler {



    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ZborException.class)
    public ModelAndView handlerZborException(ZborException zborException){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.getModel().put("exception",zborException.getErrorCodeZbor());
        modelAndView.setViewName("zborException");
        return modelAndView;
    }

}
