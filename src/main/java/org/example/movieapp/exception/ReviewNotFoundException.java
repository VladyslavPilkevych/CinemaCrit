/**
 * Custom exception class for review not found.
 */
package org.example.movieapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {

    /**
     * Constructs a new ReviewNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ReviewNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new ReviewNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


// throw new ReviewNotFoundException("Review with id " + reviewId + " not found");