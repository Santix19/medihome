package medihome;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Atencion medica registrada durante un servicio domiciliario. Solo la puede
 * crear un ServicioDomiciliario (constructor de paquete) y es duena de sus
 * mediciones de signos vitales (composicion).
 */
public class AtencionMedica {

    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFinalizacion;
    private String observaciones;
    private String recomendaciones;
    private final List<MedicionSignos> mediciones;

    AtencionMedica(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.mediciones = new ArrayList<>();
    }

    /** Registra el cierre de la atencion. */
    public void registrar(LocalDateTime fechaHoraFinalizacion, String observaciones,
                          String recomendaciones) {
        if (fechaHoraFinalizacion.isBefore(fechaHoraInicio)) {
            throw new IllegalArgumentException("La finalizacion no puede ser antes del inicio.");
        }
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
        this.observaciones = observaciones;
        this.recomendaciones = recomendaciones;
    }

    /** Crea y guarda una medicion de signos vitales que pertenece a esta atencion. */
    public MedicionSignos registrarMedicion(LocalDateTime fechaHora, double temperatura,
                                            int frecuenciaCardiaca, int presionSistolica,
                                            int presionDiastolica, double saturacionOxigeno) {
        MedicionSignos medicion = new MedicionSignos(fechaHora, temperatura, frecuenciaCardiaca,
                presionSistolica, presionDiastolica, saturacionOxigeno);
        mediciones.add(medicion);
        return medicion;
    }

    public List<MedicionSignos> getMediciones() {
        return Collections.unmodifiableList(mediciones);
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFinalizacion() {
        return fechaHoraFinalizacion;
    }

    public void setFechaHoraFinalizacion(LocalDateTime fechaHoraFinalizacion) {
        this.fechaHoraFinalizacion = fechaHoraFinalizacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }
}
