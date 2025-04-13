package co.edu.uniquindio.soft.main;

import co.edu.uniquindio.soft.model.CitaService;
import com.sun.net.httpserver.HttpServer;
import co.edu.uniquindio.soft.metrics.MetricsConfig;
import io.micrometer.core.instrument.Counter;
import io.micrometer.prometheus.PrometheusMeterRegistry;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class DevOpsPVMApp {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        PrometheusMeterRegistry prometheusRegistry = MetricsConfig.getRegistry();
        Counter counter = prometheusRegistry.counter("citas_agendadas_total");

        CitaService service = new CitaService();

        server.createContext("/", exchange -> {
            boolean citaAgendada = service.agendarCita("Andrés", "2025-04-03");
            if (citaAgendada) {
                counter.increment(); // Aumenta la métrica
            }

            String response = "📅 Cita agendada: " + citaAgendada +
                    "\n🧪 " + service.consultarResultado("123") +
                    "\n🔔 Alerta enviada: " + service.enviarAlertaSalud("Recuerda tu cita mañana a las 9 AM");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });


        server.createContext("/metrics", exchange -> {
            String response = prometheusRegistry.scrape();
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        server.start();
        System.out.println("Servidor escuchando en puerto " + port);
    }
}
