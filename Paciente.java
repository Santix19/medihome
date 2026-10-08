package medihome;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Paciente de MediHome. Puede solicitar varios servicios domiciliarios.
 */
public class Paciente extends Usuario {

    private String telefono;
    private String direccionPrincipal;
    private final List<ServicioDomiciliario> servicios;

    public Paciente() {
        super();
        this.servicios = new ArrayList<>();
    }

    public Paciente(String identificacion, String nombre, String correoElectronico,
                    String telefono, String direccionPrincipal) {
        super(identificacion, nombre, correoElectronico);
        this.telefono = telefono;
        this.direccionPrincipal = direccionPrincipal;
        this.servicios = new ArrayList<>();
    }

    /**
     * Crea un servicio domiciliario en estado SOLICITADO asociado a este paciente.
     */
    public ServicioDomiciliario solicitarServicio(String codigoUnico, LocalDateTime fechaHora,
                                                  String direccionAtencion, String motivo) {
        ServicioDomiciliario servicio =
                new ServicioDomiciliario(codigoUnico, fechaHora, direccionAtencion, motivo, this);
        servicios.add(servicio);
        recibirNotificacion(new Notificacion(
                "Su solicitud de servicio " + codigoUnico + " fue registrada."));
        return servicio;
    }

    public List<ServicioDomiciliario> getServicios() {
        return servicios;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccionPrincipal() {
        return direccionPrincipal;
    }

    public void setDireccionPrincipal(String direccionPrincipal) {
        this.direccionPrincipal = direccionPrincipal;
    }
}
