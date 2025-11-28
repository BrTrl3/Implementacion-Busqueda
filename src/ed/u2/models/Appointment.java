package ed.u2.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Appointment(String id, String lastName, String dateTimeStr) {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    //Convierte dateTimeStr en LocalDateTime.
    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(dateTimeStr, FORMATTER);
    }

    @Override
    public String toString() {
        return "%s %s (%s)".formatted(id, lastName, dateTimeStr);
    }
}
