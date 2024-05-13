/**
 * Custom IOException class.
 */
package org.example.movieapp.util;

public class IOException extends Exception {
    @java.io.Serial
    static final long serialVersionUID = 7818375828146090155L;

    /**
     * Constructs a new IOException with null as its detail message.
     */
    public IOException() {
        super();
    }

    /**
     * Constructs a new IOException with the specified detail message.
     *
     * @param message the detail message
     */
    public IOException(String message) {
        super(message);
    }

    /**
     * Constructs a new IOException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause
     */
    public IOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new IOException with the specified cause and a detail message of (cause==null ? null : cause.toString()) (which typically contains the class and detail message of cause).
     *
     * @param cause the cause
     */
    public IOException(Throwable cause) {
        super(cause);
    }
}
