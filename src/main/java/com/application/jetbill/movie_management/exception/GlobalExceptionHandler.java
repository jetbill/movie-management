package com.application.jetbill.movie_management.exception;


import com.application.jetbill.movie_management.dto.response.ApiError;
import com.application.jetbill.movie_management.exception.strategy.ExceptionHandlerStrategy;
import com.application.jetbill.movie_management.exception.strategy.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Map<Class<? extends Exception>, ExceptionHandlerStrategy> exceptionHandlerStrategies;


    public GlobalExceptionHandler() {
        this.exceptionHandlerStrategies = new HashMap<>();
        this.exceptionHandlerStrategies.put(InvalidPasswordException.class, new InvalidPasswordExceptionHandler());
        this.exceptionHandlerStrategies.put(ObjectNotFoundException.class, new ObjectNotFoundExceptionHandler());
        this.exceptionHandlerStrategies.put(MethodArgumentTypeMismatchException.class, new MethodArgumentTypeMismatchExceptionHandler());
        this.exceptionHandlerStrategies.put(MethodArgumentNotValidException.class, new MethodArgumentNotValidExceptionHandler());
        this.exceptionHandlerStrategies.put(HttpRequestMethodNotSupportedException.class, new HttpRequestMethodNotSupportedExceptionHandler());
        this.exceptionHandlerStrategies.put(HttpMediaTypeNotSupportedException.class, new HttpRequestMethodNotSupportedExceptionHandler());
        this.exceptionHandlerStrategies.put(HttpMessageNotReadableException.class, new HttpRequestMethodNotSupportedExceptionHandler());
        this.exceptionHandlerStrategies.put(Exception.class, new GeneralExceptionHandler());
    }

    @ExceptionHandler({Exception.class,
            ObjectNotFoundException.class,
            InvalidPasswordException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<ApiError> handleAllExceptions(Exception exception,
                                                        HttpServletRequest request){
        ZoneId zoneId = ZoneId.of("America/Bogota");
        LocalDateTime timestamps = LocalDateTime.now(zoneId);
        ExceptionHandlerStrategy handlerStrategy = exceptionHandlerStrategies.getOrDefault(exception.getClass(), new GeneralExceptionHandler());
        return handlerStrategy.handleException(exception, request, timestamps);

    }
}







