package com.tsystems.javaschool.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class MyExceptionHandler {


    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView handler404() {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorCode", 404);

        return modelAndView;
    }


/*    @ExceptionHandler(value = Exception.class)
    public ModelAndView handlerGlobal(Exeption e) {
    lo
        return new ModelAndView("error");
    }*/
}
