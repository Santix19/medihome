package medihome;

import java.util.ArrayList;
import java.util.List;

/**
 * Profesional de la salud. Puede atender multiples servicios en fechas
 * diferentes y pertenecer (o cambiar) de equipo de atencion domiciliaria.
 */
public class ProfesionalSalud extends Usuario {

    private String numeroRegistroProfesional;
    private String especialidad;
    private EquipoMedico equipo;
    private final List<ServicioDomiciliario> serviciosAsignados;

    public ProfesionalSalud() {
        super();
        this.serviciosAsignados = new ArrayList<>();
    }

    public ProfesionalSalud(String identificacion, String nombre, String correoElectronico,
                            String numeroRegistroProfesional, String especialidad) {
        super(identificacion, nombre, correoElectronico);
        this.numeroRegistroProfesional = numeroRegistroProfesional;
        this.especialidad = especialidad;
        this.serviciosAsignados = new ArrayList<>();
    }

    /** Se invoca cuando un servicio es programado y se le asigna este profesional. */
    public void atenderServicio(ServicioDomiciliario servicio) {
        if (!serviciosAsignados.contains(servicio)) {
            serviciosAsignados.add(servicio);
        }
    }

    public List<ServicioDomiciliario> getServiciosAsignados() {
        return serviciosAsignados;
    }

    public String getNumeroRegistroProfesional() {
        return numeroRegistroProfesional;
    }

    public void setNumeroRegistroProfesional(String numeroRegistroProfesional) {
        this.numeroRegistroProfesional = numeroRegistroProfesional;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public EquipoMedico getEquipo() {
        return equipo;
    }

    /** Usado por EquipoMedico para mantener la relacion en ambos sentidos. */
    void setEquipo(EquipoMedico equipo) {
        this.equipo = equipo;
    }
}
