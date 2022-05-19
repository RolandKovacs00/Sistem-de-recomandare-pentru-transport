package com.krmapsgeotools.exceptions;

import com.krmapsgeotools.exceptions.utils.APIError;
import com.krmapsgeotools.exceptions.utils.ConstraintViolationExceptionCustom;
import com.krmapsgeotools.exceptions.utils.NotAllowedException;
import com.krmapsgeotools.exceptions.utils.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundException(RuntimeException exception) {

        String error = exception.getMessage() + " because something inside the object or the object was : " + exception.getCause();

        APIError apiError =
                new APIError(HttpStatus.NOT_FOUND, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAllowedException.class)
    public ResponseEntity<Object> notAllowedException(RuntimeException exception)
    {
        String error = exception.getMessage() + " because something inside the object or the object was : " + exception.getCause();

        APIError apiError =
                new APIError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> methodArgumentNotValidException() {
        APIError apiError =
                new APIError(HttpStatus.BAD_REQUEST, "Bad object request check! Check your input.",
                        "The object sent via @Request Body was incorrect, check to see," +
                        " if the fields are complete and correct.");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> methodArgumentNotValidExceptionHttp() {
        APIError apiError =
                new APIError(HttpStatus.BAD_REQUEST, "Bad object request check! Check your input.",
                        "The object sent via @Request Body was incorrect, check to see," +
                                " if the fields are complete and correct.");
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationExceptionCustom.class)
    public ResponseEntity<Object> badObjectRequest(RuntimeException exception)
    {
        String error = exception.getMessage() + " because something inside the object or the object was : " + exception.getCause();

        APIError apiError =
                new APIError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
