package medihome;

import java.time.LocalDateTime;

/**
 * Servicio domiciliario solicitado por un unico paciente. Al programarse se le
 * asigna un profesional. La atencion medica existe solo como parte de este
 * servicio (composicion): la crea el propio servicio.
 */
public class ServicioDomiciliario {

    private String codigoUnico;
    private LocalDateTime fechaHora;
    private String direccionAtencion;
    private String motivo;
    private EstadoServicio estado;
    private final Paciente paciente;
    private ProfesionalSalud profesional;
    private AtencionMedica atencionMedica;

    public ServicioDomiciliario(String codigoUnico, LocalDateTime fechaHora,
                                String direccionAtencion, String motivo, Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("Todo servicio debe pertenecer a un paciente.");
        }
        this.codigoUnico = codigoUnico;
        this.fechaHora = fechaHora;
        this.direccionAtencion = direccionAtencion;
        this.motivo = motivo;
        this.paciente = paciente;
        this.estado = EstadoServicio.SOLICITADO;
    }

    /** Asigna el profesional y deja el servicio en estado PROGRAMADO. */
    public void programar(ProfesionalSalud profesional) {
        validarEstado(EstadoServicio.SOLICITADO, "programar");
        this.profesional = profesional;
        profesional.atenderServicio(this);
        this.estado = EstadoServicio.PROGRAMADO;
        String msg = "Servicio " + codigoUnico + " programado para " + Formato.fecha(fechaHora);
        paciente.recibirNotificacion(new Notificacion(msg + " con " + profesional.getNombre() + "."));
        profesional.recibirNotificacion(new Notificacion(msg + " en " + direccionAtencion + "."));
    }

    /** El profesional inicia la atencion: se crea la AtencionMedica del servicio. */
    public AtencionMedica iniciarAtencion(LocalDateTime fechaHoraInicio) {
        validarEstado(EstadoServicio.PROGRAMADO, "iniciar la atencion de");
        this.atencionMedica = new AtencionMedica(fechaHoraInicio);
        this.estado = EstadoServicio.EN_ATENCION;
        return atencionMedica;
    }

    /** Cierra la atencion medica y finaliza el servicio. */
    public void finalizar(LocalDateTime fechaHoraFinalizacion, String observaciones,
                          String recomendaciones) {
        validarEstado(EstadoServicio.EN_ATENCION, "finalizar");
        atencionMedica.registrar(fechaHoraFinalizacion, observaciones, recomendaciones);
        this.estado = EstadoServicio.FINALIZADO;
        paciente.recibirNotificacion(new Notificacion(
                "Su servicio " + codigoUnico + " fue finalizado. Recomendaciones: " + recomendaciones));
    }

    public void cancelar() {
        if (estado == EstadoServicio.FINALIZADO) {
            throw new IllegalStateException("No se puede cancelar un servicio finalizado.");
        }
        this.estado = EstadoServicio.CANCELADO;
        paciente.recibirNotificacion(new Notificacion("Su servicio " + codigoUnico + " fue cancelado."));
    }

    private void validarEstado(EstadoServicio esperado, String accion) {
        if (estado != esperado) {
            throw new IllegalStateException("No se puede " + accion + " el servicio "
                    + codigoUnico + " en estado " + estado + ".");
        }
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public void setCodigoUnico(String codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getDireccionAtencion() {
        return direccionAtencion;
    }

    public void setDireccionAtencion(String direccionAtencion) {
        this.direccionAtencion = direccionAtencion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public EstadoServicio getEstado() {
        return estado;
    }

    public void setEstado(EstadoServicio estado) {
        this.estado = estado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public ProfesionalSalud getProfesional() {
        return profesional;
    }

    public AtencionMedica getAtencionMedica() {
        return atencionMedica;
    }
}
