package com.wander.coding.challenge.common.advice;

import com.wander.coding.challenge.common.dataaccess.model.ErrorException;
import com.wander.coding.challenge.common.dataaccess.model.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Created by Amol Zore on Date: 21-06-2020
 * Email: zore.amol@gmail.com
 */
@ControllerAdvice
public class CommonControllerAdvice extends ResponseEntityExceptionHandler {

    private final Logger logger = Logger.getLogger("CommonControllerAdvice");

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error->getErrorMessage(error.getCode(), error.getArguments()))
                .collect(Collectors.toList());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("errors", errors);
        return getResponseEntity(body, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecification = ex.getMostSpecificCause();
        ErrorMessage errorMessage;

        if (Objects.nonNull(mostSpecification)) {
            String exceptionName = mostSpecification.getClass().getName();
            String message = mostSpecification.getMessage();
            errorMessage = new ErrorMessage(exceptionName, message);
        } else {
            errorMessage = new ErrorMessage(ex.getMessage(), "");
        }

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("errors", errorMessage.getMessage());
        return getResponseEntity(body, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ErrorException.class)
    @ResponseBody
    public ResponseEntity<Object> exception(final ErrorException exception) {
        String errors = getErrorMessage(exception.getErrorType().getMessageCode(), exception.getVars());
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("error", errors);
        logger.info(errors);
        return getResponseEntity(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void handleLoggerException(Exception e) {
        e.printStackTrace();
    }

    private String getErrorMessage(String errorCode, Object... vars) {
        return messageSource.getMessage(errorCode, vars, Locale.ENGLISH);
    }

    private ResponseEntity getResponseEntity(Object obj, HttpHeaders responseHeader, HttpStatus httpStatus) {
        return new ResponseEntity<>(obj, responseHeader, httpStatus );
    }
}
