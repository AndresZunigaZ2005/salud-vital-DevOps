package co.edu.uniquindio.soft.main;

import co.edu.uniquindio.soft.model.CitaService;

public class DevOpsPVMApp {
    public static void main(String[] args) {
        CitaService service = new CitaService();

        // Simulación de uso desde consola
        System.out.println("📅 Agendando cita: " + service.agendarCita("Andrés", "2025-04-03"));
        System.out.println("🧪 Consultando examen: " + service.consultarResultado("123"));
        System.out.println("🔔 Enviando alerta: " + service.enviarAlertaSalud("Recuerda tu cita mañana a las 9 AM"));
    }
}
