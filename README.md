# MediHome – Sistema de servicios médicos domiciliarios

Proyecto de clase: diagrama de clases UML (Visual Paradigm) y programa en Java del caso **MediHome**.

## Integrantes

- Esteban Santiago Narváez Narváez

## Contenido del repositorio

| Ruta | Descripción |
|---|---|
| `diagrama/MediHome.vpp` | Fuente del diagrama de clases (Visual Paradigm) |
| `diagrama/MediHome.png` | Imagen del diagrama de clases |
| `src/medihome/` | Código fuente en Java |

## Modelo

- **INotificable** (interfaz): la implementan los usuarios que reciben notificaciones.
- **Usuario** (clase abstracta): identificación, nombre, correo electrónico. Implementa `INotificable`.
  - **Paciente** (hereda de Usuario): teléfono, dirección principal. Solicita servicios.
  - **ProfesionalSalud** (hereda de Usuario): número de registro profesional, especialidad.
- **Notificacion**: mensaje y fecha/hora. Un usuario recibe 0..* notificaciones.
- **EquipoMedico**: código, nombre, zona de cobertura. **Agregación** con ProfesionalSalud: el profesional puede cambiar de equipo sin dejar de existir.
- **ServicioDomiciliario**: código único, fecha/hora programada, dirección de atención, motivo y estado (`EstadoServicio`). Pertenece a un único paciente y se le asigna un profesional al programarse.
- **EstadoServicio** (enumeración): SOLICITADO, PROGRAMADO, EN_ATENCION, FINALIZADO, CANCELADO.
- **AtencionMedica**: fecha/hora de inicio y finalización, observaciones y recomendaciones. **Composición** con ServicioDomiciliario: solo existe como parte del servicio.
- **MedicionSignos**: fecha/hora, temperatura, frecuencia cardíaca, presión sistólica y diastólica, saturación de oxígeno. **Composición** con AtencionMedica (0..* mediciones por atención).

## Ejecución

Requiere Java 11 o superior. Desde la carpeta raíz del repositorio:

```bash
javac -d out src/medihome/*.java
java -cp out medihome.Main
```

El programa pide por terminal los datos del paciente, del profesional y su equipo, del servicio domiciliario, de la atención y de las mediciones de signos vitales. Al final imprime el **reporte de la atención prestada al paciente**.

Las fechas se escriben como `aaaa-mm-dd hh:mm` (por ejemplo `2026-10-08 09:30`); si se presiona Enter se usa la fecha y hora actual.
