package medihome;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Utilidad para mostrar y leer fechas con el mismo formato en todo el programa. */
public final class Formato {

    public static final DateTimeFormatter FECHA_HORA = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Formato() {
    }

    public static String fecha(LocalDateTime fecha) {
        return fecha == null ? "-" : fecha.format(FECHA_HORA);
    }
}
