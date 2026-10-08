package medihome;

import java.util.ArrayList;
import java.util.List;

/**
 * Superclase abstracta: tanto los pacientes como los profesionales
 * de salud son usuarios del sistema y pueden recibir notificaciones.
 */
public abstract class Usuario implements INotificable {

    private String identificacion;
    private String nombre;
    private String correoElectronico;
    private final List<Notificacion> notificaciones;

    public Usuario() {
        this.notificaciones = new ArrayList<>();
    }

    public Usuario(String identificacion, String nombre, String correoElectronico) {
        this();
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.correoElectronico = correoElectronico;
    }

    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        notificaciones.add(notificacion);
    }

    @Override
    public List<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return nombre + " (ID " + identificacion + ", " + correoElectronico + ")";
    }
}
