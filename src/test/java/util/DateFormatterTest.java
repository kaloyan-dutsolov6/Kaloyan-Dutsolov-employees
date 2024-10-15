package util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateFormatterTest {

    @ParameterizedTest
    @CsvSource({
            "2024-10-15, 2024-10-15",
            "2024/10/15, 2024-10-15",
            "15-10-2024, 2024-10-15",
            "15/10/2024, 2024-10-15",
            "15.10.2024, 2024-10-15",
            "15 Oct 2024, 2024-10-15",
            "15 October 2024, 2024-10-15",
            "15-10-2024, 2024-10-15",
            "15/10/2024, 2024-10-15",
            "15.10.2024, 2024-10-15",
            "'Oct 15, 2024', '2024-10-15'",
            "20241015, 2024-10-15"
    })
    public void parseDateTest_SuccessParseDate(String input, String expected) {
        LocalDate expectedDate = LocalDate.parse(expected, DateTimeFormatter.ISO_LOCAL_DATE);
        assertEquals(expectedDate, DateFormatter.parseDate(input));
    }

    @Test
    public void parseDateTest_ThrowsException() {
        String invalidDate = "invalid-date-format";
        assertThrows(IllegalArgumentException.class, () -> DateFormatter.parseDate(invalidDate));
    }

    @ParameterizedTest
    @CsvSource({
            "2024-01-01, 2024-02-01, 2024-01-10, 2024-02-10, 23",
            "2024-01-01, 2024-01-31, 2024-02-01, 2024-02-15, 0",
            "2024-01-01, 2024-01-31, 2023-02-01, 2023-02-15, 0",
            "2024-01-01, 2024-01-10, 2024-01-10, 2024-01-20, 1",
            "2024-01-01, 2024-02-01, 2023-01-01, 2024-01-01, 1",
            "2024-01-01, 2024-02-15, 2024-01-10, 2024-02-01, 23",
            "2024-01-01, 2024-02-10, 2024-02-01, 2024-02-15, 10",
            "2024-02-01, 2024-03-01, 2024-01-01, 2024-02-15, 15"
    })
    public void calculateDaysBetweenRangeTest_SuccessDayRange(String start1, String end1, String start2, String end2, long expectedDays) {
        LocalDate s1 = LocalDate.parse(start1);
        LocalDate e1 = LocalDate.parse(end1);
        LocalDate s2 = LocalDate.parse(start2);
        LocalDate e2 = LocalDate.parse(end2);

        long actualDays = DateFormatter.calculateDaysBetweenRange(s1, e1, s2, e2);
        assertEquals(expectedDays, actualDays);
    }

}
