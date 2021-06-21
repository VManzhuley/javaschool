package com.tsystems.javaschool.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@Log4j2
@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView handler404() {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorCode", 404);

        return modelAndView;
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView handlerGlobal(Exception e) {
        log.error(e.getMessage());
        return new ModelAndView("error");
    }
}
