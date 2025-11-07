package com.singabenkosimpungose.taskmanagement.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.http.HttpStatus;

import java.net.URI;

@ControllerAdvice   //If the error is not handled in the service or the controller, it will handle it
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException error, WebRequest request){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, error.getMessage());
        problemDetail.setType(URI.create("https://technologyadvice.com/blog/information-technology/api-error/")); //A link to a documnentation for more info in the error
        problemDetail.setTitle("RESOURCE NT FOUND");
        problemDetail.setInstance(URI.create(request.getDescription(false)));   //Description of wjat happened
        
        return problemDetail;
    }

}
