package medihome;

import java.util.List;

/**
 * Contrato para los usuarios del sistema que pueden recibir notificaciones
 * relacionadas con los servicios que les corresponden.
 */
public interface INotificable {

    void recibirNotificacion(Notificacion notificacion);

    List<Notificacion> getNotificaciones();
}
