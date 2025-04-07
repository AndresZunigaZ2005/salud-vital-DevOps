package co.edu.uniquindio.soft;

import co.edu.uniquindio.soft.model.CitaService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CitaServiceTest {

    CitaService service = new CitaService();

    @Test
    void testAgendarCitaValida() {
        assertTrue(service.agendarCita("Juan Pérez", "2025-04-03"));
    }

    @Test
    void testAgendarCitaInvalida() {
        assertFalse(service.agendarCita(null, ""));
    }

    @Test
    void testConsultarResultadoExistente() {
        assertEquals("Resultado: Negativo", service.consultarResultado("123"));
    }

    @Test
    void testConsultarResultadoNoExiste() {
        assertEquals("Examen no encontrado", service.consultarResultado("999"));
    }

    @Test
    void testEnviarAlertaSaludValida() {
        assertTrue(service.enviarAlertaSalud("Tómate la presión cada 8 horas"));
    }

    @Test
    void testEnviarAlertaSaludInvalida() {
        assertFalse(service.enviarAlertaSalud(" "));
    }
}