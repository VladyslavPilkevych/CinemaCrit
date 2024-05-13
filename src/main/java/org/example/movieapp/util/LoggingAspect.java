/**
 * LoggingAspect class for logging movie controller method calls.
 */
package org.example.movieapp.util;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut for methods in MovieController class.
     */
    @Pointcut("execution(* org.example.movieapp.controller.MovieController.*(..))")
    private void movieControllerMethods() {}

    /**
     * Logs movie controller method calls.
     */
    @Before("movieControllerMethods()")
    public void logMovieControllerMethods() {
        System.out.println("movie controller calls");
        logger.info("movie controller calls");
    }
}
