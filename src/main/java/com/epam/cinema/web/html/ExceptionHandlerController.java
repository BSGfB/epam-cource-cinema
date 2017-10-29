package com.epam.cinema.web.html;

import com.epam.cinema.dto.ErrorMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ModelAndView notFound(NoHandlerFoundException ex) {
        ModelAndView notFound = new ModelAndView("index");

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName("Not Found (404)");
        errorMessage.setMessage(ex.getMessage());

        notFound.addObject("error", errorMessage);

        return notFound;
    }

    @ExceptionHandler(Exception.class)
    protected ModelAndView exceptionHandler(Exception ex) {
        ModelAndView notFound = new ModelAndView("index");

        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setName(ex.getClass().getSimpleName());
        errorMessage.setMessage(ex.getMessage());

        notFound.addObject("error", errorMessage);

        return notFound;
    }
}
