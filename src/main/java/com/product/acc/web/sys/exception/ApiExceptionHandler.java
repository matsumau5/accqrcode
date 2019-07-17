package com.product.acc.web.sys.exception;

import com.product.acc.domain.sys.domain.sys.domain.model.ApiExeptionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger logger = LoggerFactory.
            getLogger(ApiExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiExeptionInfo handleException(Exception e) {
        logger.error("ApiExceptionHandler", e);
        ApiExeptionInfo info = new ApiExeptionInfo();
        info.setCauseClass(e.getClass().getName());
        info.setErrorContent(e.getMessage());
        return info;
    }
}
