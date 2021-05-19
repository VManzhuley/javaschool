package com.tsystems.javaschool.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;


@ControllerAdvice
public class GlobalDefaultExceptionHandler {


    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("errorCode", 404);
        modelAndView.setViewName("error");

        return modelAndView;
    }


    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(Exception ex) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("error");

        return modelAndView;
    }
}
