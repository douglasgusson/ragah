package br.com.ragah.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

/**
 *
 * @author douglas
 */
public class DateUtils {

    
    public LocalDate calendarToLocalDate(Calendar calendar) {

        try {
            Instant instant = calendar.toInstant();
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            LocalDate ld = dateTime.toLocalDate();
            return (ld);
        } catch (Exception e) {
            throw new GenericException("Falha ao tentar converter a data", e);
        }
    }

    
}
