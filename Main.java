package medihome;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Programa principal de MediHome. Pide los datos por terminal, instancia un
 * paciente, un profesional, un servicio domiciliario, una atencion medica y
 * sus mediciones de signos vitales, y al final presenta el reporte de la
 * atencion prestada al paciente.
 */
public class Main {

    private static final Scanner ENTRADA = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("==============================================");
        System.out.println("   MEDIHOME - Servicios medicos domiciliarios");
        System.out.println("==============================================");

        // 1. Paciente
        titulo("1. Datos del paciente");
        Paciente paciente = new Paciente(
                leerTexto("Identificacion: "),
                leerTexto("Nombre: "),
                leerTexto("Correo electronico: "),
                leerTexto("Telefono: "),
                leerTexto("Direccion principal: "));

        // 2. Profesional de salud y su equipo
        titulo("2. Datos del profesional de salud");
        ProfesionalSalud profesional = new ProfesionalSalud(
                leerTexto("Identificacion: "),
                leerTexto("Nombre: "),
                leerTexto("Correo electronico: "),
                leerTexto("Numero de registro profesional: "),
                leerTexto("Especialidad: "));

        titulo("3. Equipo de atencion domiciliaria del profesional");
        EquipoMedico equipo = new EquipoMedico(
                leerTexto("Codigo del equipo: "),
                leerTexto("Nombre del equipo: "),
                leerTexto("Zona de cobertura: "));
        equipo.agregarProfesional(profesional);

        // 3. Servicio domiciliario
        titulo("4. Solicitud del servicio domiciliario");
        String codigo = leerTexto("Codigo unico del servicio: ");
        LocalDateTime fechaProgramada = leerFecha("Fecha y hora programada");
        String direccion = leerTextoOpcional("Direccion de atencion [Enter = "
                + paciente.getDireccionPrincipal() + "]: ", paciente.getDireccionPrincipal());
        String motivo = leerTexto("Motivo de la solicitud: ");

        ServicioDomiciliario servicio =
                paciente.solicitarServicio(codigo, fechaProgramada, direccion, motivo);
        System.out.println("-> Servicio creado en estado " + servicio.getEstado());
        servicio.programar(profesional);
        System.out.println("-> Servicio " + servicio.getEstado() + " con " + profesional.getNombre());

        // 4. Atencion medica
        titulo("5. Atencion medica");
        AtencionMedica atencion = servicio.iniciarAtencion(leerFecha("Fecha y hora de inicio"));
        System.out.println("-> Servicio " + servicio.getEstado());

        // 5. Mediciones de signos vitales
        titulo("6. Mediciones de signos vitales");
        int cantidad = leerEntero("Cuantas mediciones va a registrar? (1-10): ", 1, 10);
        for (int i = 1; i <= cantidad; i++) {
            System.out.println("  Medicion #" + i);
            atencion.registrarMedicion(
                    leerFecha("  Fecha y hora de la medicion"),
                    leerDecimal("  Temperatura (C): ", 30, 45),
                    leerEntero("  Frecuencia cardiaca (lpm): ", 20, 250),
                    leerEntero("  Presion sistolica (mmHg): ", 50, 260),
                    leerEntero("  Presion diastolica (mmHg): ", 30, 160),
                    leerDecimal("  Saturacion de oxigeno (%): ", 50, 100));
        }

        // 6. Cierre de la atencion
        titulo("7. Cierre de la atencion");
        LocalDateTime fin;
        while (true) {
            fin = leerFecha("Fecha y hora de finalizacion");
            if (!fin.isBefore(atencion.getFechaHoraInicio())) {
                break;
            }
            System.out.println("  La finalizacion no puede ser anterior al inicio ("
                    + Formato.fecha(atencion.getFechaHoraInicio()) + ").");
        }
        servicio.finalizar(fin,
                leerTexto("Observaciones clinicas: "),
                leerTexto("Recomendaciones: "));

        imprimirReporte(servicio, equipo);
        ENTRADA.close();
    }

    private static void imprimirReporte(ServicioDomiciliario servicio, EquipoMedico equipo) {
        Paciente p = servicio.getPaciente();
        ProfesionalSalud pr = servicio.getProfesional();
        AtencionMedica a = servicio.getAtencionMedica();

        System.out.println();
        System.out.println("==============================================");
        System.out.println("     REPORTE DE ATENCION DOMICILIARIA");
        System.out.println("==============================================");
        System.out.println("SERVICIO");
        System.out.println("  Codigo:            " + servicio.getCodigoUnico());
        System.out.println("  Fecha programada:  " + Formato.fecha(servicio.getFechaHora()));
        System.out.println("  Direccion:         " + servicio.getDireccionAtencion());
        System.out.println("  Motivo:            " + servicio.getMotivo());
        System.out.println("  Estado:            " + servicio.getEstado());
        System.out.println("PACIENTE");
        System.out.println("  " + p);
        System.out.println("  Telefono:          " + p.getTelefono());
        System.out.println("  Direccion:         " + p.getDireccionPrincipal());
        System.out.println("PROFESIONAL");
        System.out.println("  " + pr);
        System.out.println("  Registro:          " + pr.getNumeroRegistroProfesional());
        System.out.println("  Especialidad:      " + pr.getEspecialidad());
        System.out.println("  Equipo:            " + equipo);
        System.out.println("ATENCION MEDICA");
        System.out.println("  Inicio:            " + Formato.fecha(a.getFechaHoraInicio()));
        System.out.println("  Finalizacion:      " + Formato.fecha(a.getFechaHoraFinalizacion()));
        System.out.println("  Observaciones:     " + a.getObservaciones());
        System.out.println("  Recomendaciones:   " + a.getRecomendaciones());
        System.out.println("SIGNOS VITALES (" + a.getMediciones().size() + " medicion/es)");
        for (MedicionSignos m : a.getMediciones()) {
            System.out.println("  - " + m);
        }
        System.out.println("NOTIFICACIONES DEL PACIENTE");
        for (Notificacion n : p.getNotificaciones()) {
            System.out.println("  " + n);
        }
        System.out.println("NOTIFICACIONES DEL PROFESIONAL");
        for (Notificacion n : pr.getNotificaciones()) {
            System.out.println("  " + n);
        }
        System.out.println("==============================================");
    }

    // ---------- Lectura de datos por terminal ----------

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("--- " + texto + " ---");
    }

    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        if (!ENTRADA.hasNextLine()) {
            System.out.println();
            System.out.println("Entrada finalizada. Saliendo del programa.");
            System.exit(1);
        }
        return ENTRADA.nextLine().trim();
    }

    private static String leerTexto(String mensaje) {
        while (true) {
            String valor = leerLinea(mensaje);
            if (!valor.isEmpty()) {
                return valor;
            }
            System.out.println("  Este dato es obligatorio.");
        }
    }

    private static String leerTextoOpcional(String mensaje, String porDefecto) {
        String valor = leerLinea(mensaje);
        return valor.isEmpty() ? porDefecto : valor;
    }

    private static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            String valor = leerLinea(mensaje);
            try {
                int n = Integer.parseInt(valor);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // se vuelve a pedir
            }
            System.out.println("  Ingrese un numero entero entre " + min + " y " + max + ".");
        }
    }

    private static double leerDecimal(String mensaje, double min, double max) {
        while (true) {
            String valor = leerLinea(mensaje).replace(',', '.');
            try {
                double n = Double.parseDouble(valor);
                if (n >= min && n <= max) {
                    return n;
                }
            } catch (NumberFormatException e) {
                // se vuelve a pedir
            }
            System.out.println("  Ingrese un numero entre " + min + " y " + max + ".");
        }
    }

    private static LocalDateTime leerFecha(String mensaje) {
        while (true) {
            String valor = leerLinea(mensaje + " (aaaa-mm-dd hh:mm) [Enter = ahora]: ");
            if (valor.isEmpty()) {
                return LocalDateTime.now().withSecond(0).withNano(0);
            }
            try {
                return LocalDateTime.parse(valor, Formato.FECHA_HORA);
            } catch (DateTimeParseException e) {
                System.out.println("  Formato invalido. Ejemplo: 2026-10-08 09:30");
            }
        }
    }
}
