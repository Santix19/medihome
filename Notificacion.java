package medihome;

import java.time.LocalDateTime;

/**
 * Notificacion enviada a un usuario sobre los servicios que le corresponden.
 */
public class Notificacion {

    private String mensaje;
    private LocalDateTime fechaHora;

    public Notificacion() {
        this.fechaHora = LocalDateTime.now();
    }

    public Notificacion(String mensaje) {
        this.mensaje = mensaje;
        this.fechaHora = LocalDateTime.now();
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "[" + Formato.fecha(fechaHora) + "] " + mensaje;
    }
}
