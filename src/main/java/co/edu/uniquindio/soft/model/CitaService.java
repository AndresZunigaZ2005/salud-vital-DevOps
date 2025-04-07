package co.edu.uniquindio.soft.model;

public class CitaService {

    public boolean agendarCita(String paciente, String fecha) {
        return paciente != null && fecha != null && !fecha.isEmpty();
    }

    public String consultarResultado(String idExamen) {
        if ("123".equals(idExamen)) {
            return "Resultado: Negativo";
        }
        return "Examen no encontrado";
    }

    public boolean enviarAlertaSalud(String mensaje) {
        return mensaje != null && !mensaje.trim().isEmpty();
    }
}
