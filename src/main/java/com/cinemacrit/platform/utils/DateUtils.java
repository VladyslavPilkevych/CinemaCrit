package com.cinemacrit.platform.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for date and time operations.
 * Provides methods for calculating time elapsed since a given date-time and returning it as a human-readable string.
 */
public class DateUtils {
    /**
     * Public constructor for creating a DateUtils object.
     * This constructor is intended for external use when creating an instance of DateUtils.
     */
    public DateUtils() {}

    /**
     * Gets a string representation of the time elapsed since a given date-time.
     * The method calculates the difference between the current time and the provided creation date-time
     * and returns a human-readable string such as "5 days ago" or "3 years ago".
     *
     * @param creationDateTime the creation date-time to compare against the current time.
     * @return a string representing the time elapsed since the creation date-time.
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
