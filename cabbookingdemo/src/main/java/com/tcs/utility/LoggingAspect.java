package com.tcs.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Logs exceptions thrown from service and repository classes.
 */
@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // Advice: execute when exceptions are thrown in ServiceImpl or repository
    @AfterThrowing(
        pointcut = "execution(* com.tcs.service..*(..)) || execution(* com.tcs.repository..*(..))",
        throwing = "exception"
    )
    public void logServiceException(Exception exception) {
        // Log at ERROR level
        LOGGER.error("Exception in service/repository layer: {}", exception.getMessage(), exception);
    }
}
