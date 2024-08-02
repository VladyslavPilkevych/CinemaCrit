/**
 * Custom exception class for movie not found.
 */
package org.example.movieapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MediaNotFoundException extends RuntimeException {

    /**
     * Constructs a new MovieNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public MediaNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new MovieNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public MediaNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
