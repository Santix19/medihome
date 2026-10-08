package medihome;

import java.util.ArrayList;
import java.util.List;

/**
 * Equipo de atencion domiciliaria. Agrega profesionales (agregacion):
 * un profesional puede cambiar de equipo sin dejar de existir en el sistema.
 */
public class EquipoMedico {

    private String codigo;
    private String nombre;
    private String zonaCobertura;
    private final List<ProfesionalSalud> profesionales;

    public EquipoMedico() {
        this.profesionales = new ArrayList<>();
    }

    public EquipoMedico(String codigo, String nombre, String zonaCobertura) {
        this();
        this.codigo = codigo;
        this.nombre = nombre;
        this.zonaCobertura = zonaCobertura;
    }

    public void agregarProfesional(ProfesionalSalud profesional) {
        EquipoMedico anterior = profesional.getEquipo();
        if (anterior == this) {
            return;
        }
        if (anterior != null) {
            anterior.retirarProfesional(profesional);
        }
        profesionales.add(profesional);
        profesional.setEquipo(this);
    }

    public void retirarProfesional(ProfesionalSalud profesional) {
        if (profesionales.remove(profesional)) {
            profesional.setEquipo(null);
        }
    }

    public List<ProfesionalSalud> getProfesionales() {
        return profesionales;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getZonaCobertura() {
        return zonaCobertura;
    }

    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }

    @Override
    public String toString() {
        return nombre + " (" + codigo + ", zona " + zonaCobertura + ")";
    }
}
