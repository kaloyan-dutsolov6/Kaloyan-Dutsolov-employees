package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class DateFormatter {

    private static final DateTimeFormatter[] DATE_FORMATS = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("d MMM yyyy"),
            DateTimeFormatter.ofPattern("d MMMM yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("MM.dd.yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("MMM dd, yyyy"),
            DateTimeFormatter.ofPattern("yyyyMMdd")
    };

    public static LocalDate parseDate(String dateStr) {
        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                return LocalDate.parse(dateStr, formatter);
            } catch (DateTimeParseException ignored) {
            }
        }
        throw new IllegalArgumentException("Unsupported date format: " + dateStr);
    }

    public static long calculateDaysBetweenRange(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        if (end1.isBefore(start2) || end2.isBefore(start1)) {
            return 0;
        } else if (end1.equals(start2) || end2.equals(start1)) {
            return 1;
        }

        LocalDate maxStart = start1.isAfter(start2) ? start1 : start2;
        LocalDate minEnd = end1.isBefore(end2) ? end1 : end2;

        return ChronoUnit.DAYS.between(maxStart, minEnd) + 1;
    }
}
