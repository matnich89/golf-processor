package util;

import com.mat.golfprocessor.exception.convertor.DateMismatchException;

import java.time.LocalDate;

public class DateUtil {
    public static void validateDates(final LocalDate startDate, final LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new DateMismatchException("start date cannot be after end date");
        }
    }
}
