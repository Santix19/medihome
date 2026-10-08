package medihome;

import java.time.LocalDateTime;

/**
 * Medicion de signos vitales. Pertenece exclusivamente a la atencion medica
 * en la que fue tomada, por eso solo AtencionMedica puede crearla.
 */
public class MedicionSignos {

    private LocalDateTime fechaHora;
    private double temperatura;
    private int frecuenciaCardiaca;
    private int presionSistolica;
    private int presionDiastolica;
    private double saturacionOxigeno;

    MedicionSignos(LocalDateTime fechaHora, double temperatura, int frecuenciaCardiaca,
                   int presionSistolica, int presionDiastolica, double saturacionOxigeno) {
        this.fechaHora = fechaHora;
        this.temperatura = temperatura;
        this.frecuenciaCardiaca = frecuenciaCardiaca;
        this.presionSistolica = presionSistolica;
        this.presionDiastolica = presionDiastolica;
        this.saturacionOxigeno = saturacionOxigeno;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public int getFrecuenciaCardiaca() {
        return frecuenciaCardiaca;
    }

    public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
        this.frecuenciaCardiaca = frecuenciaCardiaca;
    }

    public int getPresionSistolica() {
        return presionSistolica;
    }

    public void setPresionSistolica(int presionSistolica) {
        this.presionSistolica = presionSistolica;
    }

    public int getPresionDiastolica() {
        return presionDiastolica;
    }

    public void setPresionDiastolica(int presionDiastolica) {
        this.presionDiastolica = presionDiastolica;
    }

    public double getSaturacionOxigeno() {
        return saturacionOxigeno;
    }

    public void setSaturacionOxigeno(double saturacionOxigeno) {
        this.saturacionOxigeno = saturacionOxigeno;
    }

    @Override
    public String toString() {
        return String.format("%s | Temp: %.1f C | FC: %d lpm | PA: %d/%d mmHg | SpO2: %.1f %%",
                Formato.fecha(fechaHora), temperatura, frecuenciaCardiaca,
                presionSistolica, presionDiastolica, saturacionOxigeno);
    }
}
