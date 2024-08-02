/**
 * Utility class for date-related operations.
 */
package org.example.movieapp.util;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    /**
     * Gets a string representation of time elapsed since a given date-time.
     *
     * @param creationDateTime the creation date-time
     * @return a string representing the time elapsed since the creation date-time
     */
    public static String getAgoString(LocalDateTime creationDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();

        long years = ChronoUnit.YEARS.between(creationDateTime, currentDateTime);
        long months = ChronoUnit.MONTHS.between(creationDateTime, currentDateTime);
        long days = ChronoUnit.DAYS.between(creationDateTime, currentDateTime);
        long hours = ChronoUnit.HOURS.between(creationDateTime, currentDateTime);
        long minutes = ChronoUnit.MINUTES.between(creationDateTime, currentDateTime);

        if (years > 0) {
            return years + " year" + (years > 1 ? "s" : "") + " ago";
        } else if (months > 0) {
            return months + " month" + (months > 1 ? "s" : "") + " ago";
        } else if (days > 0) {
            return days + " day" + (days > 1 ? "s" : "") + " ago";
        } else if (hours > 0) {
            return hours + " hour" + (hours > 1 ? "s" : "") + " ago";
        } else if (minutes > 0) {
            return minutes + " minute" + (minutes > 1 ? "s" : "") + " ago";
        } else {
            return "just now";
        }
    }
}
